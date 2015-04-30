package br.com.totvs.clinica.model;

public class LoginSenha {
	
	private String login;
	private String senha;
	private int nivel;
	
	public LoginSenha() {}
	
	public LoginSenha(String login, String senha, int nivel) {
		this.login = login;
		this.senha = senha;
		this.nivel = nivel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
