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

	public MedicoDao() throws SQLException {
		this.conexao = new ConnectionProvider().getConnection();
	}

	@Override
	public List<Medico> getTodos() throws SQLException {
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade,"
				+ "especialidades FROM medico";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Medico> medicos = new ArrayList<Medico>();
		Medico medico = new Medico();
		medico.setEndereco(new Endereco());
		while (result.next()) {

			medico.setNome(result.getString("nome"));
			medico.setLogin(result.getString("login"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			medico.setLogradouro(result.getString("logradouro"));
			medico.setBairro(result.getString("bairro"));
			medico.setCidade(result.getString("cidade"));
			medico.setEspecialidades(result.getString("especialidades"));

			medicos.add(medico);
		}

		result.close();
		return medicos;
	}

	@Override
	public Medico getPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "SELECT nome, login, rg, telefone, logradouro, bairro, cidade, especialidades"
				+ " FROM medico WHERE login = " + login;
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Medico medico = new Medico();
		medico.setEndereco(new Endereco());
		while(result.next()){
			medico.setNome(result.getString("nome"));
			medico.setLogin(result.getString("login"));
			medico.setRg(result.getString("rg"));
			medico.setTelefone(result.getString("telefone"));
			medico.setLogradouro(result.getString("logradouro"));
			medico.setBairro(result.getString("bairro"));
			medico.setCidade(result.getString("cidade"));
			medico.setEspecialidades(result.getString("especialidades"));
		}
		result.close();
		return medico;
	}

	@Override
	public void inserir(Medico medico) throws SQLException {
		
		String sql = " INSERT INTO MEDICO (nome, login, rg,"
				   + "                     telefone, logradouro, bairro,"
				   + "                     cidade, especialidades) "
				   + " VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, medico.getNome());
		statement.setString(2, medico.getLogin());
		statement.setString(3, medico.getRg());
		statement.setString(4, medico.getTelefone());
		statement.setString(5, medico.getEndereco().getLogradouro());
		statement.setString(6, medico.getEndereco().getBairro());
		statement.setString(7, medico.getEndereco().getCidade());
		statement.setString(8, medico.getEspecialidades());

		statement.execute();
		statement.close();
	}
	
	public void editar(Medico medico, String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "UPDATE MEDICO SET nome=?, login=?, rg=?, telefone=?, "
				+ "								logradouro=?, bairro=?, cidade=?, especialidades=?"
				+ " WHERE login = " + login; 
		System.out.println("Executando a Statement");
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, medico.getNome());
		statement.setString(2, medico.getLogin());
		statement.setString(3, medico.getRg());
		statement.setString(4, medico.getTelefone());
		statement.setString(5, medico.getEndereco().getLogradouro());
		statement.setString(6, medico.getEndereco().getBairro());
		statement.setString(7, medico.getEndereco().getCidade());
		statement.setString(8, medico.getEspecialidades());
		statement.executeUpdate();
		statement.close();
		System.out.println("Executei a Statement");
	}
	
	public void excluirPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "DELETE FROM medico WHERE login = " + login;

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.executeUpdate();

		statement.close();

	}

}
