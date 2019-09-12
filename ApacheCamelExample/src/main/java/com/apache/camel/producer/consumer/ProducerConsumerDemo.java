package com.apache.camel.producer.consumer;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerConsumerDemo {

	
	public static void main(String [] args) throws Exception{
		
		CamelContext ctx = new DefaultCamelContext();
		
		ctx.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
			
				from("direct:start")
				
				.process(new Processor() {
					
					public void process(Exchange exchange) throws Exception {
						
						String message = exchange.getIn().getBody(String.class);
						
						message = message+"How r u ";
						
						exchange.getOut().setBody(message);
					}
				})
				
				.to("seda:end");
					
			}
		});
		ctx.start();
		ProducerTemplate pt = ctx.createProducerTemplate();
		pt.sendBody("direct:start", "Hello jithendra...");
		
		ConsumerTemplate ct = ctx.createConsumerTemplate();
		String message = ct.receiveBody("seda:end", String.class);
		System.out.println(message);
	}
}


