package br.com.pratica.camel.processos;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.pratica.camel.model.Operandos;
import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;


public class MultiplicacaoProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {
		Operandos o =  exchange.getIn().getBody(Operandos.class);
		ResponseString r = new ResponseString();
		r.setOutput(String.valueOf(o.getN1() * o.getN2()));
		
		exchange.getIn().setBody(r);
	}

}
