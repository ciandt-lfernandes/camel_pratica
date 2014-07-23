package br.com.pratica.camel.strategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import br.com.pratica.camel.model.RequestString;


/*
 * Executa uma somat�ria com os valores recebidos da agrega��o
 */
public class SomatoriaStrategy implements AggregationStrategy {
 
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    	RequestString r = new RequestString();
    	
    	//Primeira itera��o da agrega��o. No caso o oldExchange ser� nulo.
        if (oldExchange == null) {
            return newExchange;
        }
              
        Integer oldMsg = oldExchange.getIn().getBody(Integer.class);
        Integer newMsg = newExchange.getIn().getBody(Integer.class);
        
        Integer soma = oldMsg + newMsg;
        
        Message msg = new DefaultMessage();
        msg.setBody(soma);
        newExchange.setIn(msg); 
        
        return newExchange;
    }
}