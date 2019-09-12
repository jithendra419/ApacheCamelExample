package com.camel.activemq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ActiveMqConsumer {

	public static void main(String[] args) throws Exception {
		
		CamelContext  ctx = new DefaultCamelContext();
		ConnectionFactory cf = new ActiveMQConnectionFactory();
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));
		
		ctx.addRoutes(new RouteBuilder(){
			
			@Override
			public void configure() throws Exception {
			
				from("activemq:queue:my_queue").to("seda:end");
				
			}
		});
		
		ctx.start();
		ConsumerTemplate ct = ctx.createConsumerTemplate();
		while(true){
		String msg = ct.receiveBody("seda:end", String.class);
		System.out.println("msg::::"+msg);
		
		}

	}

}
