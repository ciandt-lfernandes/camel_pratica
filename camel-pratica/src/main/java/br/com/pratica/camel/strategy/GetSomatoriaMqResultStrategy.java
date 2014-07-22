package br.com.pratica.camel.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class GetSomatoriaMqResultStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange original, Exchange resource) {
		String resourceResponse = null;
		
		try 
		{
			resourceResponse =(String) resource.getIn().getBody();
		} catch (Exception e) 
		{
			resourceResponse = "Não há resultados na fila..";
		} finally
		{
			if(resourceResponse == null)
				resourceResponse = "Erro Strategy";
		}
		
		
		if (original.getPattern().isOutCapable()) {
			original.getOut().setBody(resourceResponse);
		} else {
			original.getIn().setBody(resourceResponse);
		}
		return original;
	}
}