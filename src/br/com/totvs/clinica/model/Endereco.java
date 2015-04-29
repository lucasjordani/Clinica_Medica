package br.com.totvs.clinica.model;

public class Endereco {
	
	private int codEndereco;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;	
	
	public Endereco(int codEndereco, String rua, int numero, String bairro, String cidade) {
		this.codEndereco = codEndereco;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		
	}

	public Endereco() {
		
	}

	public int getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public void setCdidade(String cidade) {
		this.cidade = cidade;
	}

	

}
