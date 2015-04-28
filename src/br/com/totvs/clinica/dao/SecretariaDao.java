package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Secretaria;

public class SecretariaDao implements Dao<Secretaria>{
	private Connection conexao;

	public SecretariaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Secretaria> getTodos() throws SQLException {
		String sql = "SELECT cod_secretaria, nome, rg, telefone, celular, cod_login, cod_endereco FROM secretaria";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Secretaria> secretarias = new ArrayList<Secretaria>();

		while (result.next()) {
			Secretaria secretaria = new Secretaria();

			secretaria.setCodSecretaria(result.getInt("cod_secretaria"));
			secretaria.setNome(result.getString("nome"));
			secretaria.setRg(result.getString("rg"));
			secretaria.setTelefone(result.getString("telefone"));
			secretaria.setCodLogin(result.getInt("cod_login"));
			secretaria.setCodEndereco(result.getInt("cod_endereco"));

			secretarias.add(secretaria);
		}

		result.close();

		return secretarias;
	}

	@Override
	public Secretaria getPorId(int id) throws SQLException {
		String sql = "SELECT (cod_secretaria, nome, rg, telefone, celular, cod_login, cod_endereco FROM secretaria WHERE "+id+" = cod_secretaria";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Secretaria secretaria = new Secretaria();
		while(result.next()){
			secretaria.setCodSecretaria(result.getInt("cod_secretaria"));
			secretaria.setNome(result.getString("nome"));
			secretaria.setRg(result.getString("rg"));
			secretaria.setTelefone(result.getString("telefone"));
			secretaria.setCodLogin(result.getInt("cod_login"));
			secretaria.setCodEndereco(result.getInt("cod_endereco"));
		}
		return secretaria;
	}

	@Override
	public void inserir(Secretaria secretaria) throws SQLException {
		
		String sql = " INSERT INTO SECRETARIA (cod_secretaria, nome, rg, "
				   + "                     telefone, celular, cod_login, "
				   + "                     cod_endereco) "
				   + " VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setInt(1, secretaria.getCodSecretaria());
		statement.setString(2, secretaria.getNome());
		statement.setString(3, secretaria.getRg());
		statement.setString(4, secretaria.getTelefone());
		statement.setInt(5, secretaria.getCodLogin());
		statement.setInt(6, secretaria.getCodEndereco());

		statement.execute();

	}

}

