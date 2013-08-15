package org.svenehrke.checkout.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	private static final String PERSISTENCE_UNIT_NAME = "person";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		showAllPeople(em);

		// Create new person
		System.out.println("adding person");
		em.getTransaction().begin();
		PersonEntity person = new PersonEntity();
		person.setFirstName("Sven");
		person.setLastName("Ehrke");
		em.persist(person);
		em.getTransaction().commit();
		System.out.println("done");

		showAllPeople(em);

		em.close();
	}

	private static void showAllPeople(final EntityManager em) {
		System.out.println("all people so far:");
		System.out.println("==================");
		Query q = em.createQuery("select t from PersonEntity t");
		List<PersonEntity> list = q.getResultList();
		for (PersonEntity todo : list) {
			System.out.println(todo);
		}
		System.out.println("Size: " + list.size());
		System.out.println("==================");
	}
}
