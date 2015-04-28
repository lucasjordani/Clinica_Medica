package br.com.totvs.clinica.model;

public class LoginSenha {
	
	private int codLogin;
	private String usuario;
	private String senha;
	private int nivel;
	
	public LoginSenha(int codLogin, String usuario, String senha, int nivel) {
		this.codLogin = codLogin;
		this.usuario = usuario;
		this.senha = senha;
		this.nivel = nivel;
	}
	
	public LoginSenha() {
		
	}

	public int getCodLogin() {
		return codLogin;
	}

	public void setCodLogin(int codLogin) {
		this.codLogin = codLogin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
