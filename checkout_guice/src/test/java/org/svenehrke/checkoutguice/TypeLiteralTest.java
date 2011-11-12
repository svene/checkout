package org.svenehrke.checkoutguice;

import com.google.inject.TypeLiteral;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertSame;

public class TypeLiteralTest {

	@Test
	public void test1() {
		TypeLiteral<List<String>> stringList = new TypeLiteral<List<String>>() {};
//		stringList.getParameterTypes()
	}


}
