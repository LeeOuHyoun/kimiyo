package or.jp.kimiyo.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import or.jp.kimiyo.dto.SampleDto;
import or.jp.kimiyo.entity.SampleTable;
import or.jp.kimiyo.service.DbSampleService;

/**
 * DB Access Sample Controller Class
 *
 * @author leeouhyoun
 *
 */
@Controller
@RequestMapping(value = "sample")
public class DbSampleController {

	private static final Logger logger = LoggerFactory.getLogger(DbSampleController.class);

	@Inject
	private DbSampleService dbSampleService;

	/**
	 * データ取得サンプル
	 *
	 * @param model 画面に返却データ
	 * @return 遷移対象画面
	 */
	@RequestMapping(value = "/dbaccess", method = RequestMethod.GET)
	public String sampleDbAccess(Model model) {
		logger.info("dbaccess start..!");

		List<SampleTable> resultList = this.dbSampleService.getDbSampleTable();
		model.addAttribute("resultList", resultList);

		logger.info("dbaccess end..!");
		return "/sample/sample_db_get";
	}

	/**
	 * データ登録サンプル
	 *
	 * @param dto 画面から取得データ
	 * @param model 画面に返却データ
	 * @return 遷移対象画面
	 */
	@RequestMapping(value = "/setSampleDb", method = RequestMethod.POST)
	public String setSampleDb(@ModelAttribute SampleDto dto, Model model) {
		logger.info("setSampleDb start..!");
		SampleTable entity = new SampleTable();

		// DTOから取得のデータをEntityにコピー
		BeanUtils.copyProperties(dto, entity);
		// entity登録
		this.dbSampleService.setSampleDb(entity);

		// データの再取得
		List<SampleTable> resultList = this.dbSampleService.getDbSampleTable();
		model.addAttribute("resultList", resultList);

		logger.info("setSampleDb end..!");
		return "/sample/sample_db_get";
	}

	/**
	 *
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getDbSampleOnNativeQuery", method = RequestMethod.POST)
	public String getDbSampleOnNativeQuery(@ModelAttribute SampleDto dto, Model model) {
		logger.info("getDbSampleOnNativeQuery start..!");
		// データの再取得
		SampleTable result = this.dbSampleService.getDbSampleOnNativeQuery(dto.getId());
		model.addAttribute("result", result);

		logger.info("getDbSampleOnNativeQuery end..!");
		return "/sample/sample_db_get";
	}
}
