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
		String sql = "SELECT cod_medico, nome, rg, telefone, celular, cod_login, endereco FROM medico";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Medico> medicos = new ArrayList<Medico>();

		while (result.next()) {
			Medico medico = new Medico();

			medico.setCodMedico(result.getInt("cod_medico"));
			medico.setNome(result.getString("nome"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			medico.setCodLogin(result.getInt("cod_login"));
			medico.setEndereco((Endereco) result.getObject("endereco"));

			medicos.add(medico);
		}

		result.close();

		return medicos;
	}

	@Override
	public Medico getPorId(int id) throws SQLException {
		String sql = "SELECT cod_medico, nome, rg, telefone, celular, cod_login, endereco FROM medico WHERE "+id+" = cod_medico";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Medico medico = new Medico();
		while(result.next()){
			medico.setCodMedico(result.getInt("cod_medico"));
			medico.setNome(result.getString("nome"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			medico.setCodLogin(result.getInt("cod_login"));
			medico.setEndereco((Endereco) result.getObject("endereco"));
		}
		return medico;
	}

	@Override
	public void inserir(Medico medico) throws SQLException {
		
		String sql = " INSERT INTO MEDICO (nome, rg, "
				   + "                     telefone, cod_login, "
				   + "                     endereco) "
				   + " VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, medico.getNome());
		statement.setString(2, medico.getRg());
		statement.setString(3, medico.getTelefone());
		statement.setInt(4, medico.getCodLogin());
		statement.setObject(5, medico.getEndereco());

		statement.execute();

	}

}
