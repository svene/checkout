package org.svenehrke.checkout.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class OptionalTest {

	@Test
	public void optional() throws Exception {

		Optional<Integer> possible = Optional.of(5);
		assertTrue(possible.isPresent());
		assertEquals(Integer.valueOf(5), possible.get());

		assertFalse(Optional.absent().isPresent());
		try {
			Optional.absent().get();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			assertEquals("value is absent", e.getMessage());
		}
		try {
			Optional.fromNullable(null).get();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			assertEquals("value is absent", e.getMessage());
		}

		assertFalse(Optional.fromNullable(null).isPresent());
		assertTrue(Optional.fromNullable(5).isPresent());

	}

	@Test
	public void defaultValue() throws Exception {

		assertEquals(Integer.valueOf(5), Optional.of(5).get());
		assertEquals(6, Optional.fromNullable(null).or(6));
		assertNull(Optional.fromNullable(null).orNull());
	}

}
