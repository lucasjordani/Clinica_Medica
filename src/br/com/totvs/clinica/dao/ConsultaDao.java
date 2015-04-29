package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Consulta;
import br.com.totvs.clinica.model.StatusConsulta;

public class ConsultaDao implements Dao<Consulta>{

		private Connection conexao;

		public ConsultaDao(Connection conexao) {
			this.conexao = conexao;
		}

		@Override
		public List<Consulta> getTodos() throws SQLException {
			String sql = "SELECT cod_consulta, cod_paciente, cod_medico, plano_saude, data_hora, status_consulta, observacao FROM consulta";

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Consulta> consultas = new ArrayList<Consulta>();

			while (result.next()) {
				Consulta consulta = new Consulta();

				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setCodPaciente(result.getInt("cod_paciente"));
				consulta.setCodMedico(result.getInt("cod_medico"));
				consulta.setPlanoSaude(result.getString("plano_saude"));
				consulta.setDataHora(result.getString("data_hora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("status_consulta"));
				consulta.setObservacao(result.getString("observacao"));
				
				consultas.add(consulta);
			}

			result.close();

			return consultas;
		}

		@Override
		public Consulta getPorId(int id) throws SQLException {
			String sql = "SELECT cod_consulta, cod_paciente, cod_medico, plano_saude, data_hora, status_consulta, observacao FROM consulta WHERE "+id+" = cod_consulta";
			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Consulta consulta = new Consulta();
			while(result.next()){
				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setCodPaciente(result.getInt("cod_paciente"));
				consulta.setCodMedico(result.getInt("cod_medico"));
				consulta.setPlanoSaude(result.getString("plano_saude"));
				consulta.setDataHora(result.getString("data_hora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("status_consulta"));
				consulta.setObservacao(result.getString("observacao"));
			}
			return consulta;
		}

		@Override
		public void inserir(Consulta consulta) throws SQLException {
			
			String sql = " INSERT INTO CONSULTA (cod_paciente, cod_medico, plano_saude, data_hora, status_consulta, observacao)"
					   + " VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, consulta.getCodPaciente());
			statement.setInt(2, consulta.getCodMedico());
			statement.setString(3, consulta.getPlanoSaude());
			statement.setString(4, consulta.getDataHora());
			statement.setObject(5, consulta.getStatusConsulta());
			statement.setString(6, consulta.getObservacao());
			statement.execute();

		}
}
