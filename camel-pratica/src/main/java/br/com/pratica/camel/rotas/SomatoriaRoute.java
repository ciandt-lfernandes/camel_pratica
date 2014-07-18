package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.CalculadoraService;
import br.com.pratica.camel.services.SomatoriaService;
import br.com.pratica.camel.strategy.*;;


 
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
	   .marshal().string()
	   .to("file:target/out");
	   
	   from("direct:getSomatoria")
	  //.from("file:target/out?move=processado&autoCreate=false").unmarshal().string()
	  .processRef("GetSomatoriaProcessor")
	  .processRef("LoggerMessageProcessor")
	  .end();
	 	
	}
	


}
