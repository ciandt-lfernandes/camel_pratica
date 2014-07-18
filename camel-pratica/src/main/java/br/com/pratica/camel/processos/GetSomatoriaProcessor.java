package br.com.pratica.camel.processos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.pratica.camel.model.Operandos;
import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;


public class GetSomatoriaProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {
		
		File arquivo = new File("target/out");  
	       
		File[] files = arquivo.listFiles();  
		
		for (File file : files) {
			
			if (file.getName().contains("ID")){
				BufferedReader br = new BufferedReader(new FileReader("target/out/" + file.getName()));  
				  
		        while(br.ready()){  
		           String linha = br.readLine();  
		           exchange.getIn().setBody(linha);  
		        }  
		        br.close();
		        file.delete();
				
			}
						
			
		}
		
		  
	}

}
