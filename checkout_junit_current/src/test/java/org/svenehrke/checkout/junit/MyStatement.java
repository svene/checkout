package org.svenehrke.checkout.junit;

import org.junit.runners.model.Statement;

class MyStatement extends Statement {
	private Statement base;
	private String classname;
	private String methodName;
	private boolean dontThrow;

	public MyStatement(Statement aBase, String aClassname, String aMethodName, boolean aDontThrow) {
		this.base = aBase;
		this.classname = aClassname;
		this.methodName = aMethodName;
		dontThrow = aDontThrow;
	}

	@Override
	public void evaluate() throws Throwable {
		System.out.printf("%s.%s: %s **************************************%n", classname, methodName, dontThrow);
		System.out.flush();
		if (dontThrow) {
		try {
			base.evaluate();
		} catch (Throwable aThrowable) {
			System.out.println("*** exception caught");
		}
		}
		else {
			base.evaluate();
		}
		System.out.println("######################################");
		System.out.flush();
	}
}
