package br.com.pratica.camel.rotas;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.SomatoriaService;
import br.com.pratica.camel.strategy.GetSomatoriaMqResultStrategy;
import br.com.pratica.camel.strategy.SomatoriaStrategy;

/*
 * Rota Somatoria - Dependendo do m�todo do servi�o /somatoria chamado, o processamento seguir� para um direct: espec�fico
 * No caso, os servi�os dispon�veis s�o:
 *  - somatoria
 *  - getSomatoria
 *  
 * O servi�o somat�ria � utilizado para agregar as mensagens de acordo com a estrat�gia do SomatoriaStrategy at� que a condi��o
 * de completude seja verdadeira. No caso, o servi�o est� fazendo uma somat�ria de 5 n�meros e guarda em uma fila de ActiveMQ
 * 
 * O servi�o getSomatoria consome a fila do ActiveMQ e retorna para o servi�o o resultado da somat�ria corrente
 */
 
public class SomatoriaRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		// CXF webservice using code first approach
	    String uri = "cxf:/somatoria?serviceClass=" + SomatoriaService.class.getName();
	    
	    from(uri)
	    .recipientList(simple("direct:${header.operationName}"))
	    .end();

	   from("direct:somatoria")
	   .processRef("SetHeaderProcessor")
	   .aggregate(new SomatoriaStrategy()).header("id").completionSize(5)
	   .processRef("LogMessageProcessor")
	   .to("activemq:queue:SomatoriaIn");
	   
	   from("direct:getSomatoria")
	   //Podemos utilizar um Processor para acessar os dados da fila e adicionar no exchange 
       //.processRef("GetSomatorioMqProcessor")
	   //Ou podemos utilizar o padr�o Enrich em conjunto com uma strategy para realiza a tarefa
	   .pollEnrich("activemq:queue:SomatoriaIn" ,500, new GetSomatoriaMqResultStrategy())
	  .end();
	}
}
