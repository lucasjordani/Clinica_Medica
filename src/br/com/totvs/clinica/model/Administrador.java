package br.com.totvs.clinica.model;

public class Administrador extends Usuario {

	public Administrador(){}
	
	public Administrador(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}
}
