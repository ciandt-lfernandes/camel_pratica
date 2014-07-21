package br.com.pratica.camel.processos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultMessage;


public class CreateMessageProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Random gerador = new Random();
		Date date = new Date();
		int ramdomValue = gerador.nextInt();
		exchange.getIn().setBody("Mensagem gerada as " + dateFormat.format(date) + ": " + ramdomValue);

	}

}
