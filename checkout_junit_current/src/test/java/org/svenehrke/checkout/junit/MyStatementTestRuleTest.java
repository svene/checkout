package org.svenehrke.checkout.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyStatementTestRuleTest {

	@Rule
	public MyStatementTestRule ilf = new MyStatementTestRule();

	@Test
	public void first() throws Exception {
		assertTrue(false);
	}

	@Test
	@DontThrow
	public void second() throws Exception {
		assertTrue(false);
	}

	@Test
	@DontThrow
	public void third() throws Exception {
		assertTrue(true);
	}
}
