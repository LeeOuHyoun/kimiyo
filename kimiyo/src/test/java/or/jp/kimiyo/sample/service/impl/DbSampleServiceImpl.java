package or.jp.kimiyo.sample.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import or.jp.kimiyo.sample.entity.SampleTable;
import or.jp.kimiyo.sample.repository.SampleTableRepository;
import or.jp.kimiyo.sample.service.DbSampleService;

@Named
@Transactional
public class DbSampleServiceImpl implements DbSampleService {

	@Inject
	SampleTableRepository sampleTableRepository;

	public List<SampleTable> getDbSampleTable() {
		return this.sampleTableRepository.findAll();
	}
}
