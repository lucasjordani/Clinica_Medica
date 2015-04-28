package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.PlanoSaude;

public class PlanoSaudeDao implements Dao<PlanoSaude>{
	
	private Connection conexao;
	
	public PlanoSaudeDao(Connection conexao) {
		this.conexao = conexao;		
	}

	public List<PlanoSaude> getTodos() throws SQLException {		
		String sql = "SELECT cod_plano_saude, plano_saude FROM plano_saude";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		List<PlanoSaude> planosSaude = new ArrayList<PlanoSaude>();
		
		while(result.next()){
			PlanoSaude planoSaude = new PlanoSaude();

			planoSaude.setCodPlanoSaude(result.getInt("cod_plano_saude"));
			planoSaude.setPlanoSaude(result.getString("plano_saude"));
			
			planosSaude.add(planoSaude);
		}
		
		result.close();
		
		return planosSaude;
	}

	public PlanoSaude getPorId(int id) throws SQLException {
		String sql = "SELECT cod_plano_saude, plano_saude FROM plano_saude WHERE "+id+" = cod_plano_saude";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		PlanoSaude planoSaude = new PlanoSaude();
		while(result.next()){
			planoSaude.setCodPlanoSaude(result.getInt("cod_plano_saude"));
			planoSaude.setPlanoSaude(result.getString("plano_saude"));
		}
		return planoSaude;
	}

	public void inserir(PlanoSaude planoSaude) throws SQLException {
		String sql = "INSERT INTO plano_saude (plano_saude) VALUES (?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, planoSaude.getPlanoSaude());
		
		statement.execute();		
	}

}
