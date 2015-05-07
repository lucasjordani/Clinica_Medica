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

		public ConsultaDao() throws SQLException {
			this.conexao = new ConnectionProvider().getConnection();
		}

		@Override
		public List<Consulta> getTodos() throws SQLException {
			String sql = "SELECT cod_consulta, paciente, medico, plano_saude, data_hora, "
					+ "status_consulta, observacao FROM consulta";

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Consulta> consultas = new ArrayList<Consulta>();

			while (result.next()) {
				Consulta consulta = new Consulta();

				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setPaciente(result.getString("paciente"));
				consulta.setMedico(result.getString("medico"));
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
		public Consulta getPorLogin(String login) throws SQLException {
			return null;
		}

		public Consulta getPorId(int id) throws SQLException {
			String sql = "SELECT cod_consulta, paciente, medico, plano_saude, data_hora, "
					+ "status_consulta, observacao FROM consulta WHERE cod_consulta =" +id;
			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Consulta consulta = new Consulta();
			while(result.next()){
				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setPaciente(result.getString("paciente"));
				consulta.setMedico(result.getString("medico"));
				consulta.setPlanoSaude(result.getString("plano_saude"));
				consulta.setDataHora(result.getString("data_hora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("status_consulta"));
				consulta.setObservacao(result.getString("observacao"));
			}
			result.close();
			return consulta;
		}

		@Override
		public void inserir(Consulta consulta) throws SQLException {
			
			String sql = " INSERT INTO CONSULTA (cod_consulta, paciente, medico, plano_saude, data_hora, "
					+ "status_consulta, observacao"
					   + " VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setString(1, consulta.getPaciente());
			statement.setString(2, consulta.getMedico());
			statement.setString(3, consulta.getPlanoSaude());
			statement.setString(4, consulta.getDataHora());
			statement.setObject(5, consulta.getStatusConsulta());
			statement.setString(6, consulta.getObservacao());
			statement.execute();
			statement.close();
		}
		
		public Consulta getPorPaciente (String paciente) throws SQLException {
			String sql = "SELECT cod_consulta, paciente, medico, plano_saude, data_hora, "
					+ "status_consulta, observacao FROM consulta WHERE "+paciente+" = paciente";
			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Consulta consulta = new Consulta();
			while(result.next()){
				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setPaciente(result.getString("paciente"));
				consulta.setMedico(result.getString("medico"));
				consulta.setPlanoSaude(result.getString("plano_saude"));
				consulta.setDataHora(result.getString("data_hora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("status_consulta"));
				consulta.setObservacao(result.getString("observacao"));
			}
			result.close();
			return consulta;
		}
		
		public Consulta getPorMedico (String medico) throws SQLException {
			String sql = "SELECT cod_consulta, paciente, medico, plano_saude, data_hora, "
					+ "status_consulta, observacao FROM consulta WHERE "+medico+" = medico";
			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Consulta consulta = new Consulta();
			while(result.next()){
				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setPaciente(result.getString("paciente"));
				consulta.setMedico(result.getString("medico"));
				consulta.setPlanoSaude(result.getString("plano_saude"));
				consulta.setDataHora(result.getString("data_hora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("status_consulta"));
				consulta.setObservacao(result.getString("observacao"));
			}
			result.close();
			return consulta;
		}
		
		public void excluirPorId(int id) throws SQLException {
			String sql = "DELETE FROM consulta WHERE cod_consulta = " + id;
			
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.executeUpdate();

			statement.close();

		}
}
