package org.svenehrke.checkout.joor;

import org.junit.Test;

import static org.joor.Reflect.on;
import static org.junit.Assert.assertEquals;

public class MainTest {

	@Test
	public void testFirst() throws Exception {
		String world = on("java.lang.String")
			.create("Hello World")
			.call("substring", 6)
			.call("toString")
			.get();
		assertEquals("World", world);
	}
}
