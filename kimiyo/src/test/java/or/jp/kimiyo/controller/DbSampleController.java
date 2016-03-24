package or.jp.kimiyo.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.repository.SampleTableRepository;

/**
 * DB Access Sample Controller Class
 *
 * @author leeouhyoun
 *
 */
@Controller
public class DbSampleController {

	private static final Logger logger = LoggerFactory.getLogger(DbSampleController.class);

	@Inject
	private SampleTableRepository sampleTableRepository;

	@RequestMapping(value = "/sample/dbaccess", method = RequestMethod.GET)
	public String sampleDbAccess(Model model) {
		logger.info("DB Access Sample start..!");

		List<SampleTable> resultList = this.sampleTableRepository.findAll();

		logger.info("DB Access Sample end..!");
		return "/sample/sample_db_get";
	}

}
