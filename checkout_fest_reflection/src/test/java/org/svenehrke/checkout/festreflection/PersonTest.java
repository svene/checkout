package org.svenehrke.checkout.festreflection;

import org.junit.Test;

import static org.fest.reflect.core.Reflection.*;
import static org.junit.Assert.assertEquals;


public class PersonTest {
	@Test
	public void testName() throws Exception {
		Person person = constructor().withParameterTypes(String.class)
			.in(Person.class)
			.newInstance("Yoda");

		assertEquals("Yoda", person.getName());

		method("setName").withParameterTypes(String.class).in(person).invoke("Luke");
		assertEquals("Luke", person.getName());

		field("name").ofType(String.class).in(person).set("Anakin");
		assertEquals("Anakin", person.getName());

	}
}
