package org.svenehrke.checkout.jpaspringdata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
		IPersonDAO personDAO = ctx.getBean(IPersonDAO.class);

		List<PersonEntity> all = personDAO.findAll();
		showAllPeople(all);

		PersonEntity person = new PersonEntity();
		person.setFirstName("Sven");
		person.setLastName("Ehrke");

		personDAO.saveAndFlush(person);

		all = personDAO.findAll();
		showAllPeople(all);

		PersonEntity sven = personDAO.findByFirstName("Sven");
		System.out.println(sven);
	}

	private static void showAllPeople(final List<PersonEntity> list) {
		System.out.println("all people so far:");
		System.out.println("==================");
		for (PersonEntity todo : list) {
			System.out.println(todo);
		}
		System.out.println("Size: " + list.size());
		System.out.println("==================");
	}
}
