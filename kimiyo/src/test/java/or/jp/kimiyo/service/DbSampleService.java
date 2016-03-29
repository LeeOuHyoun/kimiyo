package or.jp.kimiyo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import or.jp.kimiyo.entity.Sample2Table;
import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.repository.Sample2TableRepository;
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

	@Inject
	private Sample2TableRepository sample2TableRepository;

	/**
	 * 「sample_table」テーブルからデータ取得
	 *
	 * @return
	 */
	public List<SampleTable> getDbSampleTable() {
		return this.sampleTableRepository.findAll();
	}

	/**
	 * 「sample2_table」テーブルからデータ取得
	 *
	 * @return
	 */
	public List<Sample2Table> getDbSample2Table() {
		return this.sample2TableRepository.findAll();
	}

	/**
	 * 「sample_table」テーブルにデータ登録
	 *
	 * @param entity
	 */
	public void setSampleDb(SampleTable entity) {
		this.sampleTableRepository.save(entity);
	}

}
