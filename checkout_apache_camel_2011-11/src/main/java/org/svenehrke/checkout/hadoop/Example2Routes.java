package org.svenehrke.checkout.hadoop;

import org.apache.camel.builder.RouteBuilder;

public class Example2Routes extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct:say_hello")
			.setBody(constant("Hello ").append(body()))
			.to("stream:out")
		;
	}
}
