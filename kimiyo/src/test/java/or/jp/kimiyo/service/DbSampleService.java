package or.jp.kimiyo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.repository.SampleTableRepository;

/**
 * 「sample_table」テーブルからデータ取得
 *
 * @author olee
 *
 */
@Service
public class DbSampleService {

	/** sample Repository */
	@Inject
	private SampleTableRepository sampleTableRepository;

	/**
	 * 「sample_table」テーブルからデータ取得
	 *
	 * @return
	 */
	public List<SampleTable> getDbSampleTable() {
		return this.sampleTableRepository.findAll();
	}

}
