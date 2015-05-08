package br.com.totvs.clinica.model;

public enum StatusConsulta {
	AGENDADA(1),
	COMPARECIDA(2),
	NAO_COMPARECIDA(3);
	
	private int status;
	
	private StatusConsulta(int status){
		this.status = status;
	}
	
	public int getNumeroStatus(){
		return status;
	}
	
	public static StatusConsulta getStatusPorNumero(int numero){
		for(StatusConsulta status : StatusConsulta.values()){
			if(numero == status.getNumeroStatus()){
				return status;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
}