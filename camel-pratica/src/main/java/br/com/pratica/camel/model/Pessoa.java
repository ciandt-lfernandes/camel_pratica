package br.com.pratica.camel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table (name="pessoa", schema="camel_pratica")



public class Pessoa {
	public static final String BUSCA_PESSOA_POR_NOME = "pessoa.buscarPessoaPorNome";
	
	@Id
	@SequenceGenerator(name = "SeqPessoaGenerator", sequenceName = "SEQ_PESSOA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator="SeqPessoaGenerator")
	private Integer id; 
	private String nome;
	private String endereco;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
