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
			String sql = "SELECT codConsulta, dataHora, statusConsulta, codMedico, codPaciente FROM consulta";

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Consulta> consultas = new ArrayList<Consulta>();

			while (result.next()) {
				Consulta consulta = new Consulta();

				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setDataHora(result.getString("dataHora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("statusConsulta"));
				consulta.setCodMedico(result.getInt("codMedico"));
				consulta.setCodPaciente(result.getInt("codPaciente"));

				consultas.add(consulta);
			}

			result.close();

			return consultas;
		}

		@Override
		public Consulta getPorId(int id) throws SQLException {
			String sql = "SELECT codConsulta, dataHora, statusConsulta, codMedico, codPaciente FROM consulta WHERE "+id+" = cod_consulta";
			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Consulta consulta = new Consulta();
			while(result.next()){
				consulta.setCodConsulta(result.getInt("cod_consulta"));
				consulta.setDataHora(result.getString("dataHora"));
				consulta.setStatusConsulta((StatusConsulta) result.getObject("statusConsulta"));
				consulta.setCodMedico(result.getInt("codMedico"));
				consulta.setCodPaciente(result.getInt("codPaciente"));
			}
			return consulta;
		}

		@Override
		public void inserir(Consulta consulta) throws SQLException {
			
			String sql = " INSERT INTO CONSULTA (dataHora, statusConsulta, "
					   + "                     codMedico, codPaciente)"
					   + " VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setString(1, consulta.getDataHora());
			statement.setObject(2, consulta.getStatusConsulta());
			statement.setInt(3, consulta.getCodMedico());
			statement.setInt(4, consulta.getCodPaciente());
			statement.execute();

		}
}
