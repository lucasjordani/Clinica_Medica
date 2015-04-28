package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;

public class EnderecoDao implements Dao<Endereco> {

	private Connection conexao;

	public EnderecoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Endereco> getTodos() throws SQLException {
		String sql = "SELECT cod_endereco, rua, bairro, cep, numero FROM endereco";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Endereco> enderecos = new ArrayList<Endereco>();

		while (result.next()) {
			Endereco endereco = new Endereco();

			endereco.setCodEndereco(result.getInt("cod_endereco"));
			endereco.setRua(result.getString("rua"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCep(result.getString("cep"));
			endereco.setNumero(result.getInt("numero"));

			enderecos.add(endereco);
		}

		result.close();

		return enderecos;
	}

	@Override
	public Endereco getPorId(int id) throws SQLException {
		String sql = "SELECT cod_endereco, rua, bairro, cep, numero FROM endereco WHERE "+id+" = cod_Endereco";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Endereco endereco = new Endereco();
		while(result.next()){
			endereco.setCodEndereco(result.getInt("cod_endereco"));
			endereco.setRua(result.getString("rua"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCep(result.getString("cep"));
			endereco.setNumero(result.getInt("numero"));
		}
		return endereco;
	}

	@Override
	public void inserir(Endereco endereco) throws SQLException {
		String sql = "INSERT INTO endereco (rua, bairro, cep, numero) VALUES (?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, endereco.getRua());
		statement.setString(2, endereco.getBairro());
		statement.setString(3, endereco.getCep());
		statement.setInt(4, endereco.getNumero());

		statement.execute();

	}

}
