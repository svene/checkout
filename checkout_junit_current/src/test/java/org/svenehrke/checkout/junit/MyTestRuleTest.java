package org.svenehrke.checkout.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyTestRuleTest {

	@Rule
	public MyTestRule ilf = new MyTestRule();

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
