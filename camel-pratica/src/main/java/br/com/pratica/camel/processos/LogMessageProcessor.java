package br.com.pratica.camel.processos;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LogMessageProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		String str = exchange.getIn().getBody().toString();

		System.out.println("Log: " + str);

	}

}
