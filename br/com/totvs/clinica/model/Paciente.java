package br.com.totvs.clinica.model;

public class Paciente {
	
	private int codPaciente; 
	private String nome;
	private String telefone;
	private Endereco endereco;
	private String dataNascimento;
	
	public Paciente() {}
		
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
	
}
