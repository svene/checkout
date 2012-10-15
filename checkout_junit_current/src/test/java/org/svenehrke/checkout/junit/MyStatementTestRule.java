package org.svenehrke.checkout.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MyStatementTestRule implements TestRule {

	@Override
	public Statement apply(final Statement base, final Description description) {
		final boolean dontThrow = description.getAnnotation(DontThrow.class) != null;
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				System.out.printf("MyStatementTestRule: %s: %s **************************************%n",
					description.getClassName(), description.getMethodName(), dontThrow);
				System.out.flush();
				if (dontThrow) {
					try {
						base.evaluate();
					} catch (Throwable aThrowable) {
						System.out.println("MyStatementTestRule: *** exception caught");
					}
				}
				else {
					base.evaluate();
				}
				System.out.println("MyStatementTestRule: ######################################");
				System.out.flush();
			}
		};
	}
}
