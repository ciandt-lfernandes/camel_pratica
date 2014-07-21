package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.SomatoriaService;
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
	  .processRef("GetSomatorioMqProcessor")
	  .end();
	}
}
