package or.jp.kimiyo.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.repository.SampleTableRepository;

/**
 * 「sample類table」テーブルからデータ取得
 *
 * @author olee
 *
 */
@Service
public class DbSampleService {

	/** sample Repository */
	@Inject
	private SampleTableRepository sampleTableRepository;

	@PersistenceContext
	public EntityManager entityManager;

	/**
	 * 「sample_table」テーブルからデータ取得
	 *
	 * @return 取得データ
	 */
	public List<SampleTable> getDbSampleTable() {
		return this.sampleTableRepository.findAll();
	}

	/**
	 * 「sample_table」テーブルにデータ登録
	 *
	 * @param entity
	 */
	public void setSampleDb(SampleTable entity) {
		this.sampleTableRepository.save(entity);
	}

	/**
	 * 「sample_table」テーブルからNativeQueryでデータ取得
	 *
	 * @param id ID
	 * @return 取得データ
	 */
	public SampleTable getDbSampleOnNativeQuery(String id) {
		Query nq = entityManager.createNativeQuery("SELECT * FROM sample_table WHERE id = :id", SampleTable.class);
		nq.setParameter("id", id);
		return (SampleTable) nq.getSingleResult();
	}
}
