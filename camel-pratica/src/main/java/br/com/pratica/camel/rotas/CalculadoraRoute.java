package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.services.CalculadoraService;


/*
 * Rota Calculadora - Dependendo do m�todo do servi�o /calculadora chamado, o processamento seguir� para um direct: espec�fico 
 */
public class CalculadoraRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		//Disponibilizando o servico calculadora
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
