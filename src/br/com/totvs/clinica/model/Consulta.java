package br.com.totvs.clinica.model;

public class Consulta {
	
	private int codConsulta;
	private String dataHora;
	private StatusConsulta statusConsulta;
	private int codMedico;
	private int codPaciente;
	public enum StatusConsulta {SemComparecimento, Agendada, Realizada;};
	
	public Consulta(int codConsulta, String dataHora,
			int codMedico, int codPaciente) {
		this.codConsulta = codConsulta;
		this.dataHora = dataHora;
		this.codMedico = codMedico;
		this.codPaciente = codPaciente;
		this.statusConsulta = StatusConsulta.Agendada;
	}
	
	public Consulta() {
				
	}

	public int getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
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

	public int getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}

	public int getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	
}
