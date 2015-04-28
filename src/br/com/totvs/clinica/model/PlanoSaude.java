package br.com.totvs.clinica.model;

public class PlanoSaude {
	
	private int codPlanoSaude;
	private String planoSaude;
		
	public PlanoSaude(int codPlanoSaude, String planoSaude) {
		this.codPlanoSaude = codPlanoSaude;
		this.planoSaude = planoSaude;
	}
	
	public PlanoSaude() {
		
	}

	public int getCodPlanoSaude() {
		return codPlanoSaude;
	}

	public void setCodPlanoSaude(int codPlanoSaude) {
		this.codPlanoSaude = codPlanoSaude;
	}

	public String getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(String planoSaude) {
		this.planoSaude = planoSaude;
	}
	
	
}
