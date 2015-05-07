package br.com.totvs.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Medico;
import br.com.totvs.clinica.model.Paciente;

public class PacienteDao implements Dao<Paciente> {

	private Connection conexao;

	public PacienteDao() throws SQLException {
		this.conexao = new ConnectionProvider().getConnection();
	}

	@Override
	public List<Paciente> getTodos() throws SQLException {
		String sql = "SELECT cod_paciente, nome, telefone, logradouro, bairro, cidade, "
				+ "data_nascimento FROM paciente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		List<Paciente> pacientes = new ArrayList<Paciente>();
		Endereco endereco = new Endereco();
		while (result.next()) {
			Paciente paciente = new Paciente();

			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			paciente.setEndereco(endereco);
			paciente.setDataNascimento("data_nascimento");

			pacientes.add(paciente);
		}

		result.close();
		return pacientes;
	}

	@Override
	public Paciente getPorLogin(String login) throws SQLException {
		login = "'"+login+"'";
		String sql = "SELECT cod_paciente, nome, telefone, logradouro, bairro, cidade, "
				+ "data_nascimento FROM paciente WHERE login = " + login;
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Paciente paciente = new Paciente();
		Endereco endereco = new Endereco();
		while(result.next()){
			
			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			paciente.setEndereco(endereco);
			paciente.setDataNascimento("data_nascimento");
		}
		result.close();
		return paciente;
	}

	@Override
	public void inserir(Paciente paciente) throws SQLException {
		
		String sql = " INSERT INTO PACIENTE (nome, telefone, logradouro, bairro, cidade, "
				+ "data_nascimento) VALUES (?,?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, paciente.getNome());
		statement.setString(2, paciente.getTelefone());
		statement.setString(3, paciente.getEndereco().getLogradouro());
		statement.setString(4, paciente.getEndereco().getBairro());
		statement.setString(5, paciente.getEndereco().getCidade());
		statement.setString(6, paciente.getDataNascimento());

		statement.execute();
		statement.close();
	}
	
public void inserirBasico(Paciente paciente) throws SQLException {
		
		String sql = " INSERT INTO PACIENTE (nome, telefone) VALUES (?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setString(1, paciente.getNome());
		statement.setString(2, paciente.getTelefone());

		statement.execute();
		statement.close();
	}
	
	
	public Paciente getPorId(int id) throws SQLException {
		String sql = "SELECT cod_paciente, nome, telefone, logradouro, bairro, cidade, "
				+ "data_nascimento FROM paciente WHERE "+id+" = cod_paciente";
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Paciente paciente = new Paciente();
		Endereco endereco = new Endereco();
		while(result.next()){
			
			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			endereco.setLogradouro(result.getString("logradouro"));
			endereco.setBairro(result.getString("bairro"));
			endereco.setCidade(result.getString("cidade"));
			paciente.setEndereco(endereco);
			paciente.setDataNascimento("data_nascimento");
		}
		result.close();
		return paciente;
	}
	
	public Paciente getPorNome(String nome) throws SQLException {
		nome = "'"+nome+"'";
		String sql = "SELECT cod_paciente, nome, telefone, logradouro, bairro, cidade, "
				+ "data_nascimento FROM paciente WHERE nome = " + nome;
		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		Paciente paciente = new Paciente();
		paciente.setEndereco(new Endereco());
		while(result.next()){
			
			paciente.setCodPaciente(result.getInt("cod_paciente"));
			paciente.setNome(result.getString("nome"));
			paciente.setTelefone(result.getString("telefone"));
			paciente.setLogradouro(result.getString("logradouro"));
			paciente.setBairro(result.getString("bairro"));
			paciente.setCidade(result.getString("cidade"));
			paciente.setDataNascimento("data_nascimento");
		}
		result.close();
		return paciente;
	}
	
	public void editar(Paciente paciente, String nome) throws SQLException {
		nome = "'"+nome+"'";
		String sql = "UPDATE PACIENTE SET nome=?, telefone=?, "
				+ "								logradouro=?, bairro=?, cidade=?, data_nascimento=?"
				+ " WHERE nome = " + nome; 
		System.out.println("Executando a Statement");
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, paciente.getNome());
		statement.setString(2, paciente.getTelefone());
		statement.setString(3, paciente.getEndereco().getLogradouro());
		statement.setString(4, paciente.getEndereco().getBairro());
		statement.setString(5, paciente.getEndereco().getCidade());
		statement.setString(6, paciente.getDataNascimento());
		statement.executeUpdate();
		statement.close();
		System.out.println("Executei a Statement");
	}


}