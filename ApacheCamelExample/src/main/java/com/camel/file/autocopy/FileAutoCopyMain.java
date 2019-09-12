package com.camel.file.autocopy;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class FileAutoCopyMain {

	public static void main(String[] args) throws Exception {

		CamelContext ctx = new DefaultCamelContext();
		
		ctx.addRoutes(new FileRouter());
		
		while(true)
			ctx.start();
	}

}
