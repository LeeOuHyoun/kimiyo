package or.jp.kimiyo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.repository.SampleTableRepository;
import or.jp.kimiyo.service.DbSampleService;

@Named
public class DbSampleServiceImpl implements DbSampleService {

	@Inject
	private SampleTableRepository sampleTableRepository;

	public List<SampleTable> getDbSampleTable() {
		return this.sampleTableRepository.findAll();
	}
}
