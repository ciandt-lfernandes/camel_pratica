package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.processos.CreateMessageProcessor;
import br.com.pratica.camel.processos.LogMessageProcessor;

public class MessageCreatorRoute extends RouteBuilder {

	public void configure() throws Exception {

		
		from("timer://mytimer?period=2s")
		.process(new CreateMessageProcessor())
		.to("activemq:queue:QueueA");
		
		from("activemq:queue:QueueA")
		.process(new LogMessageProcessor())
		.end();
		
	}

}