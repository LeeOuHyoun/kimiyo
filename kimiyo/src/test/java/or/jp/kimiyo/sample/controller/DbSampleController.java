package or.jp.kimiyo.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * DB Access Sample Controller Class
 * 
 * @author leeouhyoun
 *
 */
@Controller
public class DbSampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(DbSampleController.class);
	
	@RequestMapping(value = "/sample/dbaccess", method = RequestMethod.GET)
	public String sampleDbAccess(Model model) {
		logger.info("DB Access Sample start..!");
		
		logger.info("DB Access Sample end..!");
		return "/sample/sample_db_get";
	}
	
}
