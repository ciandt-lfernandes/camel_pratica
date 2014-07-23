package br.com.pratica.camel.processos.imprimeString;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;

/*
 * Recebe uma string e response o servi�o com uma string concatenada confimando o recebimento
 */

public class ImprimeStringProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {
		RequestString reqS =  exchange.getIn().getBody(RequestString.class);
		ResponseString resS = new  ResponseString();
				
		resS.setOutput("String recebida pelo WS: " + reqS.getInput());				
		exchange.getIn().setBody(resS);
	}

}
