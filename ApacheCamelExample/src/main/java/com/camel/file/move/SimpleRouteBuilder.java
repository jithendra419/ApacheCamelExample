package com.camel.file.move;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:C:/apachecamel?noop=true").to("file:E:/apachecamelout");
	}

}
