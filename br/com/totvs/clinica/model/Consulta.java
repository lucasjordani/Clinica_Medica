package br.com.totvs.clinica.model;

public class Consulta {
	
	private int codConsulta;
	private String paciente;
	private String medico;
	private String planoSaude;
	private String dataHora;
	private StatusConsulta statusConsulta;
	private String observacao;
	
	public Consulta() {}
	
	public Consulta(int codConsulta, String paciente, String medico,
			String planoSaude, String dataHora, StatusConsulta statusConsulta,
			String observacao) {
		this.codConsulta = codConsulta;
		this.paciente = paciente;
		this.medico = medico;
		this.planoSaude = planoSaude;
		this.dataHora = dataHora;
		this.statusConsulta = statusConsulta;
		this.observacao = observacao;
	}

	public int getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}
	
	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(String planoSaude) {
		this.planoSaude = planoSaude;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public StatusConsulta getStatusConsulta() {
		return statusConsulta;
	}

	public void setStatusConsulta(StatusConsulta statusConsulta) {
		this.statusConsulta = statusConsulta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "\nConsulta n°: " + codConsulta + "\nPaciente: " + paciente + 
				"\nMédico: " + medico + "\nPlano de Saúde: " + planoSaude + 
				"\nData e Hora da Consulta: " + dataHora + 
				"\nStatus da Consulta: " + statusConsulta + "\nObservacões: " + observacao + "\n";
	}
	
}
