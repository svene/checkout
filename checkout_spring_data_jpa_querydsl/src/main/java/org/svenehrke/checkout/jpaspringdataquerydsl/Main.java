package org.svenehrke.checkout.jpaspringdataquerydsl;

import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

		// querydsl:
		LocalContainerEntityManagerFactoryBean emfb = ctx.getBean(LocalContainerEntityManagerFactoryBean.class);
		EntityManager em = emfb.getNativeEntityManagerFactory().createEntityManager();

		QPersonEntity people = new QPersonEntity("people");
		JPAQuery query = new JPAQuery(em);
		PersonEntity sven = query.from(people).where(people.firstName.eq("Sven")).uniqueResult(people);
		System.out.println("Using querydsl: " + sven);

		// cannot reuse 'people' so use a new variable:
		QPersonEntity people2 = new QPersonEntity("people2");
		List<PersonEntity> list = query.from(people2).list(people2);
		showAllPeople(list);
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
