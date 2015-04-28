package br.com.totvs.clinica.model;

public class Especialidade {
	private int codEspecialidade;
	private String especialidade;
	
		
	public Especialidade(int codEspecialidade, String especialidade) {
		this.codEspecialidade = codEspecialidade;
		this.especialidade = especialidade;
	}
	
	public Especialidade(){
		
	}
	
	public int getCodEspecialidade() {
		return codEspecialidade;
	}
	
	public void setCodEspecialidade(int codEspecialidade) {
		this.codEspecialidade = codEspecialidade;
	}
	
	public String getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
}
