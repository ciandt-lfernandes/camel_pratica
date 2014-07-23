package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.processos.CreateMessageProcessor;
import br.com.pratica.camel.processos.LogMessageProcessor;

/*
 * Rota MessageCreatorRoute - Exemplo dos componentes timer: e activemq: . 
 * A cada período de tempo, é gerado uma mensagem para uma fila QueueA. Após isso, a fila é consumida tendo o seu conteúdo logado 
 * pelo LogMessageProcessor
 */
public class MessageCreatorRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		from("timer://mytimer?period=60s")
		.process(new CreateMessageProcessor())
		.to("activemq:queue:QueueA");
		
		from("activemq:queue:QueueA")
		.process(new LogMessageProcessor())
		.end();
		
	}

}