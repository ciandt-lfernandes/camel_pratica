package br.com.pratica.camel.rotas;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.SomatoriaService;
import br.com.pratica.camel.strategy.GetSomatoriaMqResultStrategy;
import br.com.pratica.camel.strategy.SomatoriaStrategy;


 
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
	   .processRef("LoggerMessageProcessor")
	   .to("activemq:queue:SomatoriaIn");
	   
	   from("direct:getSomatoria")
	   //Podemos utilizar um Processor para acessar os dados da fila e adicionar no exchange 
       //.processRef("GetSomatorioMqProcessor")
	   //Ou podemos utilizar o padrão Enrich em conjunto com uma strategy para realiza a tarefa
	   .pollEnrich("activemq:queue:SomatoriaIn" ,500, new GetSomatoriaMqResultStrategy())
	  .end();
	}
}
