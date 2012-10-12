package org.svenehrke.checkout.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MyTestRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		final boolean dontThrow = description.getAnnotation(DontThrow.class) != null;
		return new MyStatement(base, description.getClassName(), description.getMethodName(), dontThrow);
	}
}
