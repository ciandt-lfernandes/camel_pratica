package br.com.pratica.camel.processos.somatoria;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Exemplo de uso do CosumerTemplate. No caso, ele está consumindo uma fila do ActiveMQ 
 * estar dentro da rota e sim em um processor
 */
public class GetSomatorioMqProcessor implements Processor {

	@Autowired
    private ConsumerTemplate consumer;
	
	public void process(Exchange exchange) throws Exception {
		try
		{
			System.out.println("Acessando resultado do somatório...");	
			
			//Consumindo a fila SomatoriaIn do ActiveMQ
			consumer.start();
			String soma = (String) consumer.receiveBody("activemq:queue:SomatoriaIn",500);
			consumer.stop();
			
			if(soma==null)
				soma = "Não há resultados na fila"; 
			
			System.out.println("Enviando resultado do somatório: " + soma);
			exchange.getIn().getExchange().getIn().setBody(""+soma);
		}
		catch (Exception e) 
		{
			exchange.getIn().getExchange().getIn().setBody("Erro");
			System.out.println(e.toString());
		}
		
	}

}
