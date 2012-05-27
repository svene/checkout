package org.svenehrke.jmeter.sampler;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class MySampler extends AbstractJavaSamplerClient {

	@Override
	public Arguments getDefaultParameters() {
		final Arguments result = new Arguments();
		result.addArgument("memcached_servers", "localhost:11211");
		result.addArgument("username", "testuser");
		result.addArgument("password", "testpassword");
		return result;
	}

	@Override
	public void setupTest(JavaSamplerContext context) {
		super.setupTest(context);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void teardownTest(JavaSamplerContext context) {
		super.teardownTest(context);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public SampleResult runTest(JavaSamplerContext aJavaSamplerContext) {
		final SampleResult result = new SampleResult();
		boolean success = true;
		result.sampleStart();

		// Test code:
		try {
			Thread.currentThread().sleep(600);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		result.sampleEnd();
		result.setSuccessful(success);
		return result;
	}
}
