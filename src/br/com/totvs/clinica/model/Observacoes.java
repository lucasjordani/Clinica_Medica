package br.com.totvs.clinica.model;

public class Observacoes {
	
	private int codObservacao;
	private String observacao;
	private int codConsulta;
	
	
	public Observacoes(int codObservacao, String observacao, int codConsulta) {
		this.codObservacao = codObservacao;
		this.observacao = observacao;
		this.codConsulta = codConsulta;
	}
	
	public Observacoes() {
		
	}


	public int getCodObservacao() {
		return codObservacao;
	}


	public void setCodObservacao(int codObservacao) {
		this.codObservacao = codObservacao;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public int getCodConsulta() {
		return codConsulta;
	}


	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}

	
}
