package br.com.pratica.camel.rotas;
import org.apache.camel.builder.RouteBuilder;

import br.com.pratica.camel.model.Pessoa;
import br.com.pratica.camel.model.ResponseString;
import br.com.pratica.camel.services.CalculadoraService;
import br.com.pratica.camel.services.PessoaService;


/*
 * Rota Pessoa - Dependendo do método do serviço /pessoa chamado, o processamento seguirá para um direct: específico 
 */
public class PessoaRoute extends RouteBuilder {

	public void configure() throws Exception {
		
		// CXF webservice using code first approach
	    String uri = "cxf:/pessoa?serviceClass=" + PessoaService.class.getName();
	    
	    from(uri)
	    .recipientList(simple("direct:${header.operationName}"))
	    .end();

	   from("direct:inserirPessoa")
	   .processRef("InserirPessoaProcessor")
	   .end();
	   
	   from("direct:consultarPessoaId")
	   .processRef("ConsultarPessoaIdProcessor")
	   .end();
	   
	   from("direct:consultarPessoaNome")
	   .processRef("ConsultarPessoaNomeProcessor")
	   .end();
	   
	   from("direct:deletarPessoaId")
	   .processRef("DeletarPessoaIdProcessor")
	   .end();
	   
	   from("direct:editarPessoa")
	   .processRef("EditarPessoaProcessor")
	   .end();
	    
		
	}
	


}
