package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.Service;


 
public class ImprimeStringRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		// CXF webservice using code first approach
	    String uri = "cxf:/services?serviceClass=" + Service.class.getName();
	    
	    from(uri)
	    .recipientList(simple("direct:${header.operationName}"))
	    .end();

	   from("direct:imprimeString")
	   .processRef("ImprimeStringProcessor")
	   .end();
		
		
	}
	


}
