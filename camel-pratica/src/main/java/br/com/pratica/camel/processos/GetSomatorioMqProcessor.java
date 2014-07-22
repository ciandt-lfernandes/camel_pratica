package br.com.pratica.camel.processos;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

public class GetSomatorioMqProcessor implements Processor {

	@Autowired
    private ConsumerTemplate consumer;
	
	public void process(Exchange exchange) throws Exception {
		try
		{
			System.out.println("Acessando resultado do somat�rio...");	
			
			consumer.start();
			String soma = (String) consumer.receiveBody("activemq:queue:SomatoriaIn",500);
			consumer.stop();
			
			if(soma==null)
				soma = "N�o h� resultados na fila"; 
			
			System.out.println("Enviando resultado do somat�rio: " + soma);
			exchange.getIn().getExchange().getIn().setBody(""+soma);
		}
		catch (Exception e) 
		{
			exchange.getIn().getExchange().getIn().setBody("Erro");
			System.out.println(e.toString());
		}
		
	}

}
