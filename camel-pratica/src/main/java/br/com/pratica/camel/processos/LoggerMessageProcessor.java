package br.com.pratica.camel.processos;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultMessage;

import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;


public class LoggerMessageProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {

		String somatoria = exchange.getIn().getBody(String.class);

		System.out.println(somatoria);

        Message msg = new DefaultMessage();
        msg.setBody(somatoria);
        
		
		exchange.setOut(msg);
		
	}

}
