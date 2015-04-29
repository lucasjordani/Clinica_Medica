package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Paciente;

public class PacienteDao implements Dao<Paciente> {

	private Connection conexao;

	public PacienteDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Paciente> getTodos() throws SQLException {
		String sql = "SELECT cod_paciente, nome, telefone, data_nascimento, cod_plano_saude, endereco FROM paciente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Paciente> pacientes = new ArrayList<Paciente>();

		while (result.next()) {
			Paciente paciente = new Paciente();

			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			paciente.setCodPlano(result.getInt("cod_plano_saude"));
			paciente.setEndereco((Endereco) result.getObject("endereco"));

			pacientes.add(paciente);
		}

		result.close();

		return pacientes;
	}

	@Override
	public Paciente getPorId(int id) throws SQLException {
		String sql = "SELECT cod_paciente, nome, telefone, data_nascimento, cod_plano_saude, endereco FROM paciente WHERE "+id+" = cod_paciente";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Paciente paciente = new Paciente();
		while(result.next()){
			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			paciente.setCodPlano(result.getInt("cod_plano_saude"));
			paciente.setEndereco((Endereco) result.getObject("endereco"));
		}
		return paciente;
	}

	@Override
	public void inserir(Paciente paciente) throws SQLException {
		
		String sql = " INSERT INTO PACIENTE (nome, telefone, data_nascimento, cod_plano_saude, endereco) "
				   + " VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, paciente.getNome());
		statement.setString(2, paciente.getTelefone());
		statement.setString(3, paciente.getDataNascimento());
		statement.setInt(4, paciente.getCodPlano());
		statement.setObject(5, paciente.getEndereco());

		statement.execute();

	}

}