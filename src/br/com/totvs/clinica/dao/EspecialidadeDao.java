package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Especialidade;

public class EspecialidadeDao implements Dao<Especialidade>{
	
	private Connection conexao;
	
	public EspecialidadeDao(Connection conexao) {
		this.conexao = conexao;		
	}

	public List<Especialidade> getTodos() throws SQLException {		
		String sql = "SELECT cod_especialidade, especialidade FROM especialidade";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		List<Especialidade> especialidades = new ArrayList<Especialidade>();
		
		while(result.next()){
			Especialidade especialidade = new Especialidade();

			especialidade.setCodEspecialidade(result.getInt("cod_especialidade"));
			especialidade.setEspecialidade(result.getString("especialidade"));
			
			especialidades.add(especialidade);
		}
		
		result.close();
		
		return especialidades;
	}

	public Especialidade getPorId(int id) throws SQLException {
		String sql = "SELECT cod_especialidade, especialidade FROM especialidade WHERE "+id+" = cod_especialidade";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Especialidade especialidade = new Especialidade();
		while(result.next()){
			especialidade.setCodEspecialidade(result.getInt("cod_especialidade"));
			especialidade.setEspecialidade(result.getString("especialidade"));
		}
		return especialidade;
	}

	public void inserir(Especialidade especialidade) throws SQLException {
		String sql = "INSERT INTO ESPECIALIDADE (especialidade) VALUES (?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, especialidade.getEspecialidade());
		
		statement.execute();		
	}

}