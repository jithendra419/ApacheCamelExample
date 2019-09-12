package com.camel.activemq;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JavaObjectToActicveMQ {

	public static void main(String[] args) throws Exception {
		
		
		CamelContext ctx = new DefaultCamelContext();
		ConnectionFactory cf = new ActiveMQConnectionFactory();
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));
		
		ctx.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				
				from("direct:start")
				.to("activemq:Queue:my_queue");
			}
			
		});
		
		Employee e = new Employee();
		e.setEid(101);
		e.setEname("jithu");
		e.setSal(500000);
		ctx.start();
		ProducerTemplate pt  =  ctx.createProducerTemplate();
		pt.sendBody("direct:start", new Date() );

	}

}
