package or.jp.kimiyo.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import or.jp.kimiyo.entity.Sample2Table;
import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.service.DbSampleService;

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
	private DbSampleService dbSampleService;

	@RequestMapping(value = "/sample/dbaccess", method = RequestMethod.GET)
	public String sampleDbAccess(Model model) {
		logger.info("DB Access Sample start..!");

		List<SampleTable> resultList = this.dbSampleService.getDbSampleTable();
		List<Sample2Table> resultList2 = this.dbSampleService.getDbSample2Table();

		logger.info("DB Access Sample end..!");
		model.addAttribute("resultList", resultList);
		model.addAttribute("resultList2", resultList2);
		return "/sample/sample_db_get";
	}

}
