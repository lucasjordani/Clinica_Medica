package br.com.totvs.clinica.model;

public class Paciente {
	
	private int codPaciente;
	private String nome;
	private String telefone;
	private String dataNascimento;
	private int codPlano;
	private int codEndereco;
		
		
	public Paciente(int codPaciente, String nome, String telefone,
			String dataNascimento, int codPlano, int codEndereco) {
		this.codPaciente = codPaciente;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.codPlano = codPlano;
		this.codEndereco = codEndereco;
	}

	public Paciente() {
		
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCodPlano() {
		return codPlano;
	}

	public void setCodPlano(int codPlano) {
		this.codPlano = codPlano;
	}

	public int getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}

		
}
