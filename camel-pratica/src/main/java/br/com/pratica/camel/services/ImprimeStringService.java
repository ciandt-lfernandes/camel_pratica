package br.com.pratica.camel.services;

import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;

/*
 * Servico - ImprimeString
 * Esse servico recebe uma string e devolve com uma string concatenada confirmando o recebimento
 */
public interface ImprimeStringService {
    
    ResponseString imprimeString(RequestString string);
   
}

