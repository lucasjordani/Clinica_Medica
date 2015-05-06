package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.MedicoDao;

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
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		int op;
		while (loop == true){
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para registrar observações de uma consulta;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			op = sc.nextInt();
			switch (op){
				case 0:
					return 0;
				case 1:
					//Implementar:
//					registraObservacoes();
					break;
				default:
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
		return 0;
		
	}
	
	public void buscaMedico(LoginSenha loginSenha){
		Medico medico = new Medico();
		try{
			MedicoDao medicoDao = new MedicoDao();
			medico = medicoDao.getPorLogin(loginSenha.getLogin());
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		this.setNome(medico.getNome());
		this.setLogin(medico.getLogin());
		this.setRg(medico.getRg());
		this.setTelefone(medico.getTelefone());
		Endereco endereco = new Endereco();
		endereco.setLogradouro(medico.getEndereco().getLogradouro());
		endereco.setBairro(medico.getEndereco().getBairro());
		endereco.setCidade(medico.getEndereco().getCidade());
		this.setEndereco(endereco);
		this.setEspecialidades(medico.getEspecialidades());
	}
	
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nLogin: " + this.getLogin() + "\nRG: " + this.getRg()
				+ "\nTelefone: " + this.getTelefone() + "\nEspecialidades: " + this.getEspecialidades()
				+ this.getEndereco().toString();
	}
	
}
