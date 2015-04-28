package br.com.totvs.clinica.model;

public class MedicoEspecialidade {
	
	private int codMedico;
	private int codEspecialidade;
			
	public MedicoEspecialidade(int codMedico, int codEspecialidade) {
		super();
		this.codMedico = codMedico;
		this.codEspecialidade = codEspecialidade;
	}
	
	public MedicoEspecialidade() {
		
	}
	
	public int getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	public int getCodEspecialidade() {
		return codEspecialidade;
	}
	public void setCodEspecialidade(int codEspecialidade) {
		this.codEspecialidade = codEspecialidade;
	}
	

}
