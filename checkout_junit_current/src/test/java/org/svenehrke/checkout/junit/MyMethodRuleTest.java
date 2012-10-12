package org.svenehrke.checkout.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyMethodRuleTest {

	@Rule
	public MyMethodRule ilf = new MyMethodRule();

	@Test
	public void first() throws Exception {
		assertTrue(false);
	}

	@Test
	public void second() throws Exception {
		assertTrue(true);
	}
}
