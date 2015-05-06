package br.com.totvs.clinica.model;

import java.util.Scanner;

public class Usuario {
	
	private String nome;
	private String login;
	private String rg;
	private String telefone;
	private Endereco endereco;
	
	public Usuario(){}
	
	public Usuario(String nome, String login, String rg, String telefone, Endereco endereco) {
		this.nome = nome;
		this.login = login;
		this.rg = rg;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void setLogradouro(String logradouro){
		this.endereco.setLogradouro(logradouro);
	}
	
	public void setBairro(String bairro){
		this.endereco.setBairro(bairro);
	}
	
	public void setCidade(String cidade){
		this.endereco.setCidade(cidade);
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\nLogin: " + login + "\nRG: " + rg
				+ "\nTelefone: " + telefone + endereco.toString();
	}

}
