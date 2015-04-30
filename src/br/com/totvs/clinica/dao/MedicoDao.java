package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Medico;

public class MedicoDao implements Dao<Medico> {

	private Connection conexao;

	public MedicoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Medico> getTodos() throws SQLException {
		String sql = "SELECT cod_medico, nome, rg, telefone, logradouro, bairro, cidade, "
				+ "cod_login FROM medico";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Medico> medicos = new ArrayList<Medico>();
		Endereco endereco = new Endereco();
		while (result.next()) {
			Medico medico = new Medico();

			medico.setCodMedico(result.getInt("cod_medico"));
			medico.setNome(result.getString("nome"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			medico.setEndereco(endereco);
			medico.setCodLogin(result.getInt("cod_login"));

			medicos.add(medico);
		}

		result.close();

		return medicos;
	}

	@Override
	public Medico getPorId(int id) throws SQLException {
		String sql = "SELECT cod_medico, nome, rg, telefone, logradouro, bairro, cidade, "
				+ "cod_login FROM medico WHERE "+id+" = cod_medico";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Medico medico = new Medico();
		Endereco endereco = new Endereco();
		while(result.next()){
			medico.setCodMedico(result.getInt("cod_medico"));
			medico.setNome(result.getString("nome"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			medico.setEndereco(endereco);
			medico.setCodLogin(result.getInt("cod_login"));
		}
		return medico;
	}

	@Override
	public void inserir(Medico medico) throws SQLException {
		
		String sql = " INSERT INTO MEDICO (nome, rg, "
				   + "                     telefone, logradouro, bairro,"
				   + "                     cidade, cod_login) "
				   + " VALUES (?,?,?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, medico.getNome());
		statement.setString(2, medico.getRg());
		statement.setString(3, medico.getTelefone());
		statement.setString(4, medico.getEndereco().getLogradouro());
		statement.setString(5, medico.getEndereco().getBairro());
		statement.setString(6, medico.getEndereco().getCidade());
		statement.setInt(7, medico.getCodLogin());

		statement.execute();

	}

}
