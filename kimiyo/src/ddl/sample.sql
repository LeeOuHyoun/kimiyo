CREATE
    TABLE public.sample_table(
        id VARCHAR(20) NOT NULL
        ,name VARCHAR(20)
        ,CONSTRAINT sample_table_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE sample_table IS 'サンプル用';
COMMENT ON COLUMN sample_table.id IS 'ID';
COMMENT ON COLUMN sample_table.name IS '名前';
