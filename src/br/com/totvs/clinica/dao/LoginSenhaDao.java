package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.LoginSenha;
import br.com.totvs.clinica.model.Usuario;

public class LoginSenhaDao implements Dao<LoginSenha>{
	
	private Connection conexao;
	
	public LoginSenhaDao() throws SQLException {
		this.conexao = new ConnectionProvider().getConnection();		
	}

	@Override
	public List<LoginSenha> getTodos() throws SQLException {		
		String sql = "SELECT login, senha, nivel FROM login_senha";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		List<LoginSenha> loginsSenhas = new ArrayList<LoginSenha>();
		
		while(result.next()){
			LoginSenha loginSenha = new LoginSenha();
			
			loginSenha.setLogin(result.getString("login"));
			loginSenha.setSenha(result.getString("senha"));
			loginSenha.setNivel(result.getInt("nivel"));
			
			loginsSenhas.add(loginSenha);
		}
		
		result.close();
		return loginsSenhas;
	}
	
	@Override
	public LoginSenha getPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "SELECT login, senha, nivel FROM login_senha WHERE login = " + login;
		
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();
		
		LoginSenha loginSenha = new LoginSenha();
		while(result.next()){
			loginSenha.setLogin(result.getString("login"));
			loginSenha.setSenha(result.getString("senha"));
			loginSenha.setNivel(result.getInt("nivel"));
		}
		result.close();
		return loginSenha;
	}

	@Override
	public void inserir(LoginSenha loginSenha) throws SQLException {
		String sql = "INSERT INTO LOGIN_SENHA (login, senha, nivel) VALUES (?,?,?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, loginSenha.getLogin());
		statement.setString(2, loginSenha.getSenha());
		statement.setInt(3, loginSenha.getNivel());
		
		statement.execute();
		statement.close();
	}
	
	public void editar(String dado, int cod, Usuario usuario) throws SQLException{
		String login = "'"+usuario.getLogin()+"'";
		String sql = null;
		switch(cod){
			case 2:
				sql = "UPDATE LOGIN_SENHA SET login = " + dado + "WHERE login = " + login;
				break;
			case 8:
				sql = "UPDATE LOGIN_SENHA SET senha = " + dado + "WHERE login = " + login;
				break;
			default:
				System.out.println("Entrei no dafault LoginSenhaDao");
				return;
		}
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.executeUpdate();
		statement.close();
	}
	
	public void excluirPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "DELETE FROM LOGIN_SENHA WHERE login = " + login;

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.executeUpdate();

		statement.close();
	}
	
}
