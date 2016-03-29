CREATE
    TABLE public.sample_table(
        id VARCHAR(20) NOT NULL
        ,name VARCHAR(20)
        ,CONSTRAINT sample_table_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE sample_table IS 'サンプル用';
COMMENT ON COLUMN sample_table.id IS 'ID';
COMMENT ON COLUMN sample_table.name IS '名前';

/********************************************************************/
-- Postgresql CREATE SEQUENCE example
-- Sequence: data_customer_total_purchase_info_error_error_id_seq
-- DROP SEQUENCE data_customer_total_purchase_info_error_error_id_seq;
CREATE SEQUENCE data_customer_total_purchase_info_error_error_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 173
  CACHE 1;

  -- default nextval example
  nextval('data_customer_total_purchase_info_error_error_id_seq'::regclass)
/********************************************************************/
-- Postgresql 関数作成Example
CREATE OR REPLACE FUNCTION f_customer_rank_list_imp(
  p_customer_no character varying[]                 -- 顧客NO
  , p_total_purchase_amount character varying[]     -- 累計購入金額
  , p_closing_day character varying[]               -- 締日
) RETURNS text AS $$
DECLARE
  -- 定数定義エリア
  t_result text := 'OK';                -- 返却値
  t_result2 text := 'OK';               -- 返却値
  i_is_customer INTEGER;                -- 顧客マスタ存在比較値
  b_is_numeric boolean;                 -- 数字、有効性チェック値
  b_is_date boolean;                    -- 日付、有効性チェック値
  i_is_info INTEGER;                    -- 会員別累計購入情報、存在チェック値

  -- ワーク定義エリア
BEGIN

RAISE NOTICE '会員ランク判定・登録処理 start time %', clock_timestamp();

   IF p_customer_no IS NOT NULL THEN
     FOR i in 1..array_length(p_customer_no, 1) LOOP
       SELECT INTO i_is_customer COUNT(customer_id) from mst_customer WHERE customer_id = p_customer_no[i] ::INTEGER;
       SELECT INTO b_is_numeric is_numeric(p_total_purchase_amount[i]);
       SELECT INTO b_is_date is_date(p_closing_day[i]);

       CASE WHEN i_is_customer = 1 AND b_is_numeric IS TRUE AND b_is_date IS TRUE THEN
         SELECT INTO i_is_info COUNT(customer_no) FROM data_customer_total_purchase_info WHERE customer_no = p_customer_no[i];
         CASE WHEN i_is_info = 1 THEN
             INSERT
               INTO
                 data_customer_total_purchase_info_history(
                   customer_no
                   ,total_purchase_amount
                   ,closing_day
                   ,regist_timestamp
                   ,regist_id
                   ,update_timestamp
                   ,update_id
                   ,delete_flag
                   )
                   SELECT
                     *
                   FROM
                     data_customer_total_purchase_info
                   WHERE
                     customer_no = p_customer_no[i];

             UPDATE
               data_customer_total_purchase_info
             SET
               total_purchase_amount = p_total_purchase_amount[i] ::INTEGER
               ,closing_day = p_closing_day[i] ::DATE
               ,update_timestamp = CURRENT_TIMESTAMP
               ,update_id = 'rank_batch'
               ,delete_flag = '0'
             WHERE
               customer_no = p_customer_no[i];
         ELSE
           INSERT
             INTO
               data_customer_total_purchase_info(
                 customer_no
                 ,total_purchase_amount
                 ,closing_day
                 ,regist_timestamp
                 ,regist_id
                 ,update_timestamp
                 ,update_id
                 ,delete_flag
           )
           VALUES(
             p_customer_no[i]
             ,p_total_purchase_amount[i] ::INTEGER
             ,p_closing_day[i] ::DATE
             ,CURRENT_TIMESTAMP
             ,'rank_batch'
             ,CURRENT_TIMESTAMP
             ,'rank_batch'
             ,'0'
           );
         END CASE;

       ELSE
         -- 会員別累計購入情報取込エラー登録（顧客NO）
         INSERT
           INTO
             data_customer_total_purchase_info_error(
                 error_code
                 ,customer_no
                 ,total_purchase_amount
                 ,closing_day
                 ,line_no
                 ,regist_timestamp
                 ,regist_id
                 ,update_timestamp
                 ,update_id
                 ,delete_flag
             )
           VALUES
            (
                 CASE
                   WHEN i_is_customer = 0 THEN 1
                   WHEN b_is_numeric IS FALSE THEN 2
                   WHEN b_is_date IS FALSE THEN 3
                 END
                 ,p_customer_no[i]
                 ,p_total_purchase_amount[i]
                 ,p_closing_day[i]
                 ,i
                 ,CURRENT_TIMESTAMP
                 ,'rank_batch'
                 ,CURRENT_TIMESTAMP
                 ,'rank_batch'
                 ,'0'
            );

       END CASE;
     END LOOP;
   END IF;

RAISE NOTICE '会員ランク判定・登録処理 end time %', clock_timestamp();
  RETURN t_result;

EXCEPTION WHEN OTHERS THEN
   GET STACKED DIAGNOSTICS t_result = RETURNED_SQLSTATE
                         , t_result2 = MESSAGE_TEXT;
   RETURN t_result || ' ' || t_result2;

END;
$$ LANGUAGE plpgsql;
/********************************************************************/