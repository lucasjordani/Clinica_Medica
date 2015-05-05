package br.com.totvs.clinica.model;

public class Medico extends Usuario {
	
	private String especialidades;

	public Medico() {}
	
	public Medico(String nome, String login, String rg, String telefone, 
			Endereco endereco, String especialidades) {
		super(nome, login, rg, telefone, endereco);
		this.especialidades = especialidades;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}

	public int operaMedico() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nLogin: " + this.getLogin() + "\nRG: " + this.getRg()
				+ "\nTelefone: " + this.getTelefone() + "\nEspecialidades: " + this.getEspecialidades()
				+ this.getEndereco().toString();
	}
	
}
