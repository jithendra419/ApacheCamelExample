package com.camel.file.move;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

	public static void main(String[] args) {
		
		SimpleRouteBuilder srb = new SimpleRouteBuilder();
		CamelContext cc = new DefaultCamelContext();
		try{
			cc.addRoutes(srb);
			cc.start();
			Thread.sleep(5 * 60 * 1000);
            cc.stop();
			
		}catch (Exception e) {
            e.printStackTrace();
        }

	}

}
