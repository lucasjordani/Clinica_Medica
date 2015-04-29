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
		String sql = "SELECT cod_administrador, nome, rg, telefone, celular, cod_login, endereco FROM administrador";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Administrador> administradores = new ArrayList<Administrador>();

		while (result.next()) {
			Administrador administrador = new Administrador();

			administrador.setCodAdministrador(result.getInt("cod_administrador"));
			administrador.setNome(result.getString("nome"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			administrador.setCodLogin(result.getInt("cod_login"));
			administrador.setEndereco((Endereco) result.getObject("endereco"));

			administradores.add(administrador);
		}

		result.close();

		return administradores;
	}

	@Override
	public Administrador getPorId(int id) throws SQLException {
		String sql = "SELECT cod_administrador, nome, rg, telefone, celular, cod_login, endereco FROM administrador WHERE "+id+" = cod_administrador";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Administrador administrador = new Administrador();
		while(result.next()){
			administrador.setCodAdministrador(result.getInt("cod_administrador"));
			administrador.setNome(result.getString("nome"));
			administrador.setRg(result.getString("rg"));
			administrador.setTelefone(result.getString("telefone"));
			administrador.setCodLogin(result.getInt("cod_login"));
			administrador.setEndereco((Endereco) result.getObject("endereco"));
		}
		return administrador;
	}

	@Override
	public void inserir(Administrador administrador) throws SQLException {
		
		String sql = " INSERT INTO ADMINISTRADOR (nome, rg, "
				   + "                     telefone, cod_login, "
				   + "                     endereco) "
				   + " VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, administrador.getNome());
		statement.setString(2, administrador.getRg());
		statement.setString(3, administrador.getTelefone());
		statement.setInt(4, administrador.getCodLogin());
		statement.setObject(5, administrador.getEndereco());

		statement.execute();

	}
}
