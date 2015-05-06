package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Usuario;

public class AdministradorDao implements Dao<Administrador> {

	private Connection conexao;

	public AdministradorDao() throws SQLException {
		this.conexao = new ConnectionProvider().getConnection();
	}

	@Override
	public List<Administrador> getTodos() throws SQLException {
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade"
				+ "FROM administrador";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Administrador> administradores = new ArrayList<Administrador>();
		Administrador administrador = new Administrador();
		administrador.setEndereco(new Endereco());
		while (result.next()) {

			administrador.setNome(result.getString("nome"));
			administrador.setLogin(result.getString("login"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			administrador.setLogradouro(result.getString("logradouro"));
			administrador.setBairro(result.getString("bairro"));
			administrador.setCidade(result.getString("cidade"));

			administradores.add(administrador);
		}

		result.close();
		return administradores;
	}

	@Override
	public Administrador getPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade"
				+ " FROM administrador WHERE login = "+ login;
		
		System.out.println(sql);

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();		

		Administrador administrador = new Administrador();
		administrador.setEndereco(new Endereco());
		while (result.next()) {
			System.out.println("OI");
			administrador.setNome(result.getString("nome"));
			administrador.setLogin(result.getString("login"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			administrador.setLogradouro(result.getString("logradouro"));
			administrador.setBairro(result.getString("bairro"));
			administrador.setCidade(result.getString("cidade"));
		}
		result.close();
		return administrador;
	}

	@Override
	public void inserir(Administrador administrador) throws SQLException {

		String sql = " INSERT INTO ADMINISTRADOR (nome, login, rg, telefone, "
				+ "								logradouro, bairro, cidade)"
				+ " VALUES (?,?,?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, administrador.getNome());
		statement.setString(2, administrador.getLogin());
		statement.setString(3, administrador.getRg());
		statement.setString(4, administrador.getTelefone());
		statement.setString(5, administrador.getEndereco().getLogradouro());
		statement.setString(6, administrador.getEndereco().getBairro());
		statement.setString(7, administrador.getEndereco().getCidade());

		statement.execute();
		statement.close();
	}
	
	public void editar(Administrador administrador, String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "UPDATE ADMINISTRADOR SET nome=?, login=?, rg=?, telefone=?, "
				+ "								logradouro=?, bairro=?, cidade=?"
				+ " WHERE login = " + login; 
		System.out.println("Executando a Statement");
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, administrador.getNome());
		statement.setString(2, administrador.getLogin());
		statement.setString(3, administrador.getRg());
		statement.setString(4, administrador.getTelefone());
		statement.setString(5, administrador.getEndereco().getLogradouro());
		statement.setString(6, administrador.getEndereco().getBairro());
		statement.setString(7, administrador.getEndereco().getCidade());
		statement.executeUpdate();
		statement.close();
		System.out.println("Executei a Statement");
	}

	public void excluirPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "DELETE FROM administrador WHERE login = " + login;

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.executeUpdate();

		statement.close();

	}

}
