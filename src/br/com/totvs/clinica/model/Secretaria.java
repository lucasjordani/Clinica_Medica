package br.com.totvs.clinica.model;

public class Secretaria extends Usuario {
	
	public Secretaria(){}

	public Secretaria(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}
			
}
