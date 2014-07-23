package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.processos.CreateMessageProcessor;
import br.com.pratica.camel.processos.LogMessageProcessor;

/*
 * Rota MessageCreatorRoute - Exemplo dos componentes timer: e activemq: . 
 * A cada per�odo de tempo, � gerado uma mensagem para uma fila QueueA. Ap�s isso, a fila � consumida tendo o seu conte�do logado 
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