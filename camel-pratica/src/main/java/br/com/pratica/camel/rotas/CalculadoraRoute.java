package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.CalculadoraService;


 
public class CalculadoraRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		// CXF webservice using code first approach
	    String uri = "cxf:/calculadora?serviceClass=" + CalculadoraService.class.getName();
	    
	    from(uri)
	    .recipientList(simple("direct:${header.operationName}"))
	    .end();

	   from("direct:soma")
	   .processRef("SomaProcessor")
	   .end();
	   
	   from("direct:subtracao")
	   .processRef("SubtracaoProcessor")
	   .end();
	   
	   from("direct:multiplicacao")
	   .processRef("MultiplicacaoProcessor")
	   .end();
	   
	   from("direct:divisao")
	   .processRef("DivisaoProcessor")
	   .end();
		
		
	}
	


}
