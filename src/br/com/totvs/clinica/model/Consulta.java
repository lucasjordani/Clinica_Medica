package br.com.totvs.clinica.model;

public class Consulta {
	
	private int codConsulta;
	private int codPaciente;
	private int codMedico;
	private String planoSaude;
	private String dataHora;
	private StatusConsulta statusConsulta;
	private String observacao;
	
		
	public Consulta(int codConsulta, int codPaciente, int codMedico,
			String planoSaude, String dataHora, StatusConsulta statusConsulta,
			String observacao) {
		super();
		this.codConsulta = codConsulta;
		this.codPaciente = codPaciente;
		this.codMedico = codMedico;
		this.planoSaude = planoSaude;
		this.dataHora = dataHora;
		this.statusConsulta = statusConsulta;
		this.observacao = observacao;
	}

	public Consulta() {
				
	}

	public int getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}

	public int getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}

	public int getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
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

	
}
