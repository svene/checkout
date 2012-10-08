package org.svenehrke.checkout.guava;

import org.junit.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PreconditionsTest {

	@Test
	public void preconditions() throws Exception {
		String name = null;
		try {
			checkArgument(name != null, "name must not be null", name);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			assertEquals("name must not be null [null]", e.getMessage());
		}
	}
}
