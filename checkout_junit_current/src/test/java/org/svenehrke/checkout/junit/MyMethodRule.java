package org.svenehrke.checkout.junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class MyMethodRule implements MethodRule {
	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		String classname = method.getMethod().getDeclaringClass().getSimpleName();
		String methodName = method.getName();
		return new MyStatement(base, classname, methodName, method.getMethod().getAnnotation(DontThrow.class) != null);
	}

}
