package br.com.pratica.camel.dao;

import br.com.pratica.camel.model.Pessoa;



public interface Dao {
	
	public String persistir(Pessoa pessoa);
	public Pessoa consultaId(Integer id);
	public Pessoa consultaNome(String nome);
	public String deletarPessoa(Integer id);
	public String editarPessoa(Pessoa p);
	 
	
}
