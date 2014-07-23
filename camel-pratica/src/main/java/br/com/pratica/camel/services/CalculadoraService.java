package br.com.pratica.camel.services;

import br.com.pratica.camel.model.Operandos;
import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;

/*
 * Servico - Calculadora
 * Esse servico disponibiliza operacoes basicas sob 2 operandos
 */

public interface CalculadoraService {
	
    ResponseString soma(Operandos o);
    ResponseString subtracao(Operandos o);
    ResponseString multiplicacao(Operandos o);
    ResponseString divisao(Operandos o);
    
}

