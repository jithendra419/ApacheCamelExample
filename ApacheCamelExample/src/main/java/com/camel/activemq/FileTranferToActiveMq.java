package com.camel.activemq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileTranferToActiveMq {

	public static void main(String[] args) throws Exception {
		
		
		CamelContext ctx = new DefaultCamelContext();
		ConnectionFactory cf = new ActiveMQConnectionFactory();
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));
		
		ctx.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				
				from("file:input_box?noop=true")
				.to("activemq:Queue:my_queue");
			}
			
		});
		
		while(true)
		ctx.start();
	}

}
