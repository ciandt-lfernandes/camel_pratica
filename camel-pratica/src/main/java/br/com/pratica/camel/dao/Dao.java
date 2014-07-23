package br.com.pratica.camel.dao;

import br.com.pratica.camel.model.Pessoa;
import br.com.pratica.camel.model.ResponseStringObject;



public interface Dao {
	
	public ResponseStringObject persistir(Pessoa pessoa);
	public ResponseStringObject consultaId(Integer id);
	public ResponseStringObject consultaNome(String nome);
	public String deletarPessoa(Integer id);
	public ResponseStringObject editarPessoa(Pessoa p);
	 
	
}
