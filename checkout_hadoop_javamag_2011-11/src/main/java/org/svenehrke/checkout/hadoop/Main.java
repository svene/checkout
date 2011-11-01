package org.svenehrke.checkout.hadoop;

import org.apache.camel.impl.DefaultCamelContext;

public class Main {
	public static void main(String[] args) throws Exception {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		camelContext.start();
		Thread.sleep(10000);
		camelContext.stop();
	}
}
