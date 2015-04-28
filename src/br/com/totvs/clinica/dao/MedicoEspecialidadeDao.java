package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.MedicoEspecialidade;

public class MedicoEspecialidadeDao implements Dao<MedicoEspecialidade> {

	private Connection conexao;

	public MedicoEspecialidadeDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<MedicoEspecialidade> getTodos() throws SQLException {
		String sql = "SELECT cod_medico, cod_especialidade FROM medico_especialidade";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<MedicoEspecialidade> medicosEspecialidades = new ArrayList<MedicoEspecialidade>();

		while (result.next()) {
			MedicoEspecialidade medicoEspecialidade = new MedicoEspecialidade();

			medicoEspecialidade.setCodMedico(result.getInt("cod_medico"));
			medicoEspecialidade.setCodEspecialidade(result.getInt("cod_especialidade"));

			medicosEspecialidades.add(medicoEspecialidade);
		}

		result.close();

		return medicosEspecialidades;
	}

	@Override
	public MedicoEspecialidade getPorId(int id) throws SQLException {
		String sql = "SELECT cod_medico, cod_especialidade FROM medico_especialidade WHERE "+id+" = cod_paciente";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		MedicoEspecialidade paciente = new Paciente();
		while(result.next()){
			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			paciente.setCodPlano(result.getInt("cod_plano_saude"));
			paciente.setCodEndereco(result.getInt("cod_endereco"));
		}
		return paciente;
	}

	@Override
	public void inserir(MedicoEspecialidade paciente) throws SQLException {
		
		String sql = " INSERT INTO PACIENTE (nome, telefone, data_nascimento, cod_plano_saude, cod_endereco) "
				   + " VALUES (?, ?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, paciente.getNome());
		statement.setString(2, paciente.getTelefone());
		statement.setString(3, paciente.getDataNascimento());
		statement.setInt(4, paciente.getCodPlano());
		statement.setInt(5, paciente.getCodEndereco());

		statement.execute();

	}

}