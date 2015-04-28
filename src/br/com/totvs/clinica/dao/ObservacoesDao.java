package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Observacoes;

public class ObservacoesDao implements Dao<Observacoes>{
	
	private Connection conexao;
	
	public ObservacoesDao(Connection conexao) {
		this.conexao = conexao;		
	}

	public List<Observacoes> getTodos() throws SQLException {		
		String sql = "SELECT cod_observacao, observacao, cod_consulta FROM observacoes";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		List<Observacoes> observacoes = new ArrayList<Observacoes>();
		
		while(result.next()){
			Observacoes observacao = new Observacoes();

			observacao.setCodObservacao(result.getInt("cod_observacao"));
			observacao.setObservacao(result.getString("observacao"));
			observacao.setCodConsulta(result.getInt("cod_consulta"));
			
			observacoes.add(observacao);
		}
		
		result.close();
		
		return observacoes;
	}

	public Observacoes getPorId(int id) throws SQLException {
		String sql = "SELECT cod_observacao, observacao, cod_consulta FROM observacoes WHERE "+id+" = cod_observacao";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Observacoes observacao = new Observacoes();
		while(result.next()){
			observacao.setCodObservacao(result.getInt("cod_observacao"));
			observacao.setObservacao(result.getString("observacao"));
			observacao.setCodConsulta(result.getInt("cod_consulta"));
		}
		return observacao;
	}

	public void inserir(Observacoes observacao) throws SQLException {
		String sql = "INSERT INTO OBSERVACAO (observacao) VALUES (?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, observacao.getObservacao());
		
		statement.execute();		
	}

}
