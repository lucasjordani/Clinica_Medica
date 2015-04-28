package br.com.totvs.clinica.model;

public class Medico {
	
	private int codMedico;
	private String nome;
	private String rg;
	private String telefone;
	private int codLogin;
	private int codEndereco;
	
		
	public Medico(int codMedico, String nome, String rg, String telefone,
			int codLogin, int codEndereco) {
		this.codMedico = codMedico;
		this.nome = nome;
		this.rg = rg;
		this.telefone = telefone;
		this.codLogin = codLogin;
		this.codEndereco = codEndereco;
	}

	public Medico() {
		
	}

	public int getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
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
