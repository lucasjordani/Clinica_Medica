package br.com.totvs.clinica.model;

public class Endereco {
	
	private String logradouro;
	private String bairro;
	private String cidade;	
	
	public Endereco(String logradouro, String bairro, String cidade) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public Endereco() {
		
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
