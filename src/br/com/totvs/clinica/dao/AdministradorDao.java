package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.Endereco;

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
		Endereco endereco = new Endereco();
		while (result.next()) {
			Administrador administrador = new Administrador();

			administrador.setNome(result.getString("nome"));
			administrador.setLogin(result.getString("login"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			administrador.setEndereco(endereco);

			administradores.add(administrador);
		}

		result.close();
		return administradores;
	}

	@Override
	public Administrador getPorLogin(String login) throws SQLException {
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade"
				+ " FROM administrador WHERE login = login";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Administrador administrador = new Administrador();
		Endereco endereco = new Endereco();
		while (result.next()) {
			administrador.setNome(result.getString("nome"));
			administrador.setLogin(result.getString("login"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			administrador.setEndereco(endereco);
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

	public void excluirPorLogin(String login) throws SQLException {

		String sql = "DELETE FROM administrador WHERE login = login";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.executeUpdate();

		statement.close();
		conexao.close();

	}
}
