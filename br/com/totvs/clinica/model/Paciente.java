package br.com.totvs.clinica.model;

import java.sql.SQLException;

import br.com.totvs.clinica.dao.PacienteDao;

public class Paciente {
	
	private int codPaciente; 
	private String nome;
	private String telefone;
	private Endereco endereco;
	private String dataNascimento;
	
	public Paciente() {
		endereco = new Endereco();
	}
		
	public Paciente(int codPaciente, String nome, String telefone, 
			Endereco endereco, String dataNascimento) {
		this.codPaciente = codPaciente;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}

	public int getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void setLogradouro(String logradouro){
		this.endereco.setLogradouro(logradouro);
	}
	
	public void setBairro(String bairro){
		this.endereco.setBairro(bairro);
	}
	
	public void setCidade(String cidade){
		this.endereco.setCidade(cidade);
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String toString() {
		return "\nNome: " + nome + "\nTelefone: " + telefone +
				"Data de nascimento: " + dataNascimento + endereco.toString();
	}

//	public String toStringBasico() {
//		return "Nome: " + nome + "\nTelefone: " + telefone;
//	}
//	
//	public String toStringComplementar() {
//		return "Data de nascimento: " + dataNascimento + endereco.toString();
//	}
	
	public void buscaPaciente(String nome){
		Paciente paciente = new Paciente();
		try{
			PacienteDao pacienteDao = new PacienteDao();
			paciente = pacienteDao.getPorNome(paciente.getNome());
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		this.setNome(paciente.getNome());
		this.setTelefone(paciente.getTelefone());
		this.setDataNascimento(paciente.getDataNascimento());
		this.setLogradouro(paciente.getEndereco().getLogradouro());
		this.setBairro(paciente.getEndereco().getBairro());
		this.setCidade(paciente.getEndereco().getCidade());

	}
}
