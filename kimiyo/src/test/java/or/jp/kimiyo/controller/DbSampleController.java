package or.jp.kimiyo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@SuppressWarnings("null")
	@RequestMapping(value = "/sample/dbaccess", method = RequestMethod.GET)
	public String sampleDbAccess(Model model) {
		logger.info("DB Access Sample start..!");
		EntityManager emn = null;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		emn = emf.createEntityManager();
		Session session = emn.unwrap(Session.class);
		DbSampleService dbSample = context.getBean(DbSampleService.class);
		List<SampleTable> resultList = dbSample.getDbSampleTable();

//		  EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Query query = (Query) em.unwrap(Session.class).getNamedQuery("finishPosContract");
//        List<Integer> rec = query.list();
//        em.getTransaction().commit();
		logger.info("DB Access Sample end..!");
		return "/sample/sample_db_get";
	}

}
