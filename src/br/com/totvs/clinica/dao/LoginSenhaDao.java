package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.LoginSenha;

public class LoginSenhaDao implements Dao<LoginSenha>{
	
	private Connection conexao;
	
	public LoginSenhaDao(Connection conexao) {
		this.conexao = conexao;		
	}

	public List<LoginSenha> getTodos() throws SQLException {		
		String sql = "SELECT cod_login_senha, usuario, senha, nivel FROM login_senha";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		List<LoginSenha> loginsSenhas = new ArrayList<LoginSenha>();
		
		while(result.next()){
			LoginSenha loginSenha = new LoginSenha();

			loginSenha.setCodLogin(result.getInt("cod_login_senha"));
			loginSenha.setUsuario(result.getString("usuario"));
			loginSenha.setSenha(result.getString("senha"));
			
			loginsSenhas.add(loginSenha);
		}
		
		result.close();
		
		return loginsSenhas;
	}

	public LoginSenha getPorId(int id) {
		return null;
	}

	public void inserir(LoginSenha loginSenha) throws SQLException {
		String sql = "INSERT INTO LOGIN_SENHA (usuario, senha, nivel) VALUES (?,?,?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, loginSenha.getUsuario());
		statement.setString(2, loginSenha.getSenha());
		statement.setInt(3, loginSenha.getNivel());
		
		statement.execute();		
	}

}
