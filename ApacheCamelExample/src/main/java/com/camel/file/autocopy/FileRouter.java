package com.camel.file.autocopy;

import org.apache.camel.builder.RouteBuilder;

public class FileRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:input_box?noop=true").to("file:output_box");	
	}

}
