package or.jp.kimiyo.repository.factory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CustomRepositoryImpl<T, ID extends Serializable> extends
        SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    /** logger. */
    private static final Logger log = LoggerFactory.getLogger(CustomRepositoryImpl.class);

    private EntityManager entityManager;

    private final String historyTableSuffix = "History";

    private final String historyTablePackagePath = "jp.rizap.pos.entity.history";


    public CustomRepositoryImpl(Class<T> domainClass,
            EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public <S extends T> S saveHandleHistory(S entity) {

        List<ID> ids = new ArrayList<ID>();
        List<Object> pks = new ArrayList<Object>();

        boolean isEmbeddedId = false;
        Class cls = entity.getClass();
        while (! Object.class.equals(cls.getSuperclass())) cls = cls.getSuperclass();

        try {
            for (Field field : cls.getDeclaredFields()) {

                if (field.getAnnotation(javax.persistence.Id.class) != null) {
                    // @Id
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        ids.add((ID) field.get(entity));
                    } else {
                        StringBuilder methodBuf = new StringBuilder();
                        methodBuf.append("get");
                        methodBuf.append(Character.toUpperCase(field.getName().charAt(0)));
                        methodBuf.append(field.getName().substring(1));
                        ID id =  (ID) cls.getMethod(methodBuf.toString()).invoke(entity);
                        if (id == null) log.debug(methodBuf.toString() + "の結果がNULLです。");
                        else ids.add(id);

                    }
                }
                else if (field.getAnnotation(javax.persistence.EmbeddedId.class) != null) {
                    // @EmbeddedId
                    field.setAccessible(true);
                    pks.add(field.get(entity));
                    isEmbeddedId = true;
                }
            }
            // 履歴テーブル登録用データ取得
            List<T> historyList = null;
            if (isEmbeddedId) {
                historyList = new ArrayList<T>();
                for (Object o : pks) {
                    historyList.add((T) this.entityManager.find(cls, o));
                }
            }
            else {
                historyList = findAll(getElements(ids));
            }
            // 履歴テーブル登録処理
            if (historyList.size() > 0) {
                // 履歴テーブル取得
                String clazzName = historyTablePackagePath + "."
                        + cls.getSimpleName() + historyTableSuffix;

                Class ref = Class.forName(clazzName);
                Object historyClazz = ref.newInstance();

                CustomRepositoryImpl<T, Serializable> historyRepositoryImpl = new CustomRepositoryImpl<T, Serializable>((Class<T>) historyClazz.getClass(), this.entityManager);

                // 対象の履歴テーブルに登録
                for (T e : historyList) {
                    Object history = ref.newInstance();
                    BeanUtils.copyProperties(e, history);
                    historyRepositoryImpl.save((S) history);
                }
            }
        } catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalArgumentException | IllegalAccessException | ClassNotFoundException | InstantiationException ex) {
            log.error(ex.getMessage(), ex);
//            throw new BusinessException("COM_HISTORY_001E", ex);
        }
        return entity;
    }

    private Iterable<ID> getElements(List<ID> list) {
        return Collections.unmodifiableList(list);
    }
}
