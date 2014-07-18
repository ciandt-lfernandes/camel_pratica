package br.com.pratica.camel.processos;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.pratica.camel.model.Operandos;
import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;


public class SetHeaderProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setHeader("id", "1");
		
	}

}
