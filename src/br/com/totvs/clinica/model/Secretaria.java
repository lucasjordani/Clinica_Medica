package br.com.totvs.clinica.model;

public class Secretaria {
	
	private int codSecretaria;
	private String nome;
	private String rg;
	private String telefone;
	private int codLogin;
	private int codEndereco;
		
	public Secretaria(int codSecretaria, String nome, String rg,
			String telefone, int codLogin, int codEndereco) {
		this.codSecretaria = codSecretaria;
		this.nome = nome;
		this.rg = rg;
		this.telefone = telefone;
		this.codLogin = codLogin;
		this.codEndereco = codEndereco;
	}

	public Secretaria() {
		
	}
	
	public void cadastrarConsulta(){
		System.out.println("Digite o nome do paciente");
	}

	public int getCodSecretaria() {
		return codSecretaria;
	}

	public void setCodSecretaria(int codSecretaria) {
		this.codSecretaria = codSecretaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public int getCodLogin() {
		return codLogin;
	}

	public void setCodLogin(int codLogin) {
		this.codLogin = codLogin;
	}

	public int getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}

		
}
