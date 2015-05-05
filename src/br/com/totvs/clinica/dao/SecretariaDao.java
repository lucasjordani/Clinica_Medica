package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Secretaria;

public class SecretariaDao implements Dao<Secretaria>{
	private Connection conexao;

	public SecretariaDao() throws SQLException {
		this.conexao = new ConnectionProvider().getConnection();
	}

	@Override
	public List<Secretaria> getTodos() throws SQLException {
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade"
				+ "FROM secretaria";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Secretaria> secretarias = new ArrayList<Secretaria>();
		Endereco endereco = new Endereco();
		while (result.next()) {
			Secretaria secretaria = new Secretaria();

			secretaria.setNome(result.getString("nome"));
			secretaria.setLogin(result.getString("login"));
			secretaria.setRg(result.getString("rg"));
			secretaria.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			secretaria.setEndereco(endereco);

			secretarias.add(secretaria);
		}

		result.close();
		return secretarias;
	}

	@Override
	public Secretaria getPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade"
				+ "FROM secretaria WHERE login = " + login;
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Secretaria secretaria = new Secretaria();
		Endereco endereco = new Endereco();
		while(result.next()){
			secretaria.setNome(result.getString("nome"));
			secretaria.setLogin(result.getString("login"));
			secretaria.setRg(result.getString("rg"));
			secretaria.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			secretaria.setEndereco(endereco);
		}
		result.close();
		return secretaria;
	}

	@Override
	public void inserir(Secretaria secretaria) throws SQLException {
		
		String sql = " INSERT INTO SECRETARIA (nome, login, rg, telefone, "
				+ "								logradouro, bairro, cidade)"
				+ " VALUES (?,?,?,?,?,?,?)";
		

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, secretaria.getNome());
		statement.setString(2, secretaria.getLogin());
		statement.setString(3, secretaria.getRg());
		statement.setString(4, secretaria.getTelefone());
		statement.setString(5, secretaria.getEndereco().getLogradouro());
		statement.setString(6, secretaria.getEndereco().getBairro());
		statement.setString(7, secretaria.getEndereco().getCidade());

		statement.execute();
		statement.close();
	}
	
	public void excluirPorLogin(String login) throws SQLException {

		String sql = "DELETE FROM secretaria WHERE login = " + login;

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.executeUpdate();

		statement.close();
		conexao.close();

	}

}

