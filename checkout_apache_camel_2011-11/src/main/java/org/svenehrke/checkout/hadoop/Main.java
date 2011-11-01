package org.svenehrke.checkout.hadoop;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {
	public static void main(String[] args) throws Exception {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new Example2Routes());
		camelContext.start();

		ProducerTemplate template = camelContext.createProducerTemplate();
		template.sendBody("direct:say_hello", "Alberto");

		Thread.sleep(10000);
		camelContext.stop();
	}
}
