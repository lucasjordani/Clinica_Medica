package br.com.totvs.clinica.model;

public class Endereco {
	
	private int codEndereco;
	private String rua;
	private String bairro;
	private String cep;
	private int numero;
	
	
	public Endereco(int codEndereco, String rua, String bairro, String cep,
			int numero) {
		this.codEndereco = codEndereco;
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numero = numero;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
