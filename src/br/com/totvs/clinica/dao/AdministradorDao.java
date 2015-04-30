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

	public AdministradorDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Administrador> getTodos() throws SQLException {
		String sql = "SELECT cod_administrador, nome, rg, telefone, logradouro, bairro, cidade, "
				+ "cod_login  FROM administrador";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Administrador> administradores = new ArrayList<Administrador>();
		Endereco endereco = new Endereco();
		while (result.next()) {
			Administrador administrador = new Administrador();

			administrador.setCodAdministrador(result.getInt("cod_administrador"));
			administrador.setNome(result.getString("nome"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			administrador.setEndereco(endereco);
			administrador.setCodLogin(result.getInt("cod_login"));

			administradores.add(administrador);
		}

		result.close();

		return administradores;
	}

	@Override
	public Administrador getPorId(int id) throws SQLException {
		String sql = "SELECT cod_administrador, nome, rg, telefone, logradouro, bairro, cidade, "
				+ "cod_login  FROM administrador WHERE "+id+" = cod_administrador";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Administrador administrador = new Administrador();
		Endereco endereco = new Endereco();
		while(result.next()){
			administrador.setCodAdministrador(result.getInt("cod_administrador"));
			administrador.setNome(result.getString("nome"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			administrador.setEndereco(endereco);
			administrador.setCodLogin(result.getInt("cod_login"));
			
		}
		return administrador;
	}

	@Override
	public void inserir(Administrador administrador) throws SQLException {
		
		String sql = " INSERT INTO ADMINISTRADOR (nome, rg, "
				   + "                     telefone, logradouro, bairro,"
				   + "                     cidade, cod_login) "
				   + " VALUES (?,?,?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, administrador.getNome());
		statement.setString(2, administrador.getRg());
		statement.setString(3, administrador.getTelefone());
		statement.setString(4, administrador.getEndereco().getLogradouro());
		statement.setString(5, administrador.getEndereco().getBairro());
		statement.setString(6, administrador.getEndereco().getCidade());
		statement.setInt(7, administrador.getCodLogin());

		statement.execute();

	}
}
