package br.com.pratica.camel.strategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import br.com.pratica.camel.model.RequestString;



public class SomatoriaStrategy implements AggregationStrategy {
 
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    	RequestString r = new RequestString();
    	
        if (oldExchange == null) {
            // the first time we only have the new exchange so it wins the first round
            return newExchange;
        }
              
        Integer oldMsg = oldExchange.getIn().getBody(Integer.class);
        Integer newMsg = newExchange.getIn().getBody(Integer.class);
        
        Integer soma = oldMsg + newMsg;
        
        Message msg = new DefaultMessage();
        msg.setBody(soma);
        newExchange.setIn(msg); 
       
        //r.setInput("Somatoria ate o momento:" + String.valueOf(soma));
        //newExchange.getIn().setBody(r);
        
        return newExchange;
    }
}