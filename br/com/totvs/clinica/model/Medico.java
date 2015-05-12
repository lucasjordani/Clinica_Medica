package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.ConsultaDao;
import br.com.totvs.clinica.dao.MedicoDao;
import br.com.totvs.clinica.dao.PacienteDao;

public class Medico extends Usuario {
	
	private Medico medico;
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

	public int operaMedico() throws SQLException {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true){
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para inserir observações em uma consulta;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			switch (sc.nextInt()){
				case 0:
					return 0;
				case 1:
					buscaConsulta();
					break;
				default:
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
			}
		}
		return 0;
		
	}

	private void buscaConsulta() {
		Paciente paciente = new Paciente();
		paciente.setEndereco(new Endereco());
		List<Consulta> consultas = new ArrayList<>();
		System.out.println("Digite o nome do paciente: ");
		Scanner sc = new Scanner(System.in);
		String nome = sc.next() + sc.nextLine();
		try {
			PacienteDao pacienteDao = new PacienteDao();
			paciente = pacienteDao.getPorNome(nome);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (paciente.getNome() != null && paciente.getNome().equals(nome)){
			try {
				ConsultaDao consultaDao = new ConsultaDao();
				consultas = consultaDao.getPorPaciente(nome, this.getLogin());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Paciente não existe no sistema!");
			return;
		}
		System.out.println("Digite o código da consulta que deseja visualizar:");
		for (int  i = 0; i < consultas.size(); i++)
			System.out.println("\nConsulta n°: " + i + consultas.get(i).toString());
		Consulta consulta = new Consulta();
		consulta = consultas.get(sc.nextInt());
		System.out.println("Deseja cadastrar alguma observação nesta consulta?");
		System.out.println("Digite 1 para SIM e 0 para NÃO.");
		boolean loop = true;
		while(loop == true){
			switch(sc.nextInt()){
				case 0:
					return;
				case 1:
					registraObservacoes(consulta);
					return;
				default:
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
	}

	public void registraObservacoes(Consulta consulta) {
		Scanner sc = new Scanner(System.in);
		consulta.toString();
		System.out.println("Digite as observações na consulta: ");
		consulta.setObservacao(sc.next() + sc.nextLine());
		try {
			ConsultaDao consultaDao = new ConsultaDao();
			consultaDao.insereObservacao(consulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Observações inseridas com sucesso!");
		return;
	}
	
	public void buscaMedico(LoginSenha loginSenha){
		Medico medico = new Medico();
		medico.setEndereco(new Endereco());
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
		this.setLogradouro(medico.getEndereco().getLogradouro());
		this.setBairro(medico.getEndereco().getBairro());
		this.setCidade(medico.getEndereco().getCidade());
		this.setEspecialidades(medico.getEspecialidades());
	}
	
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nLogin: " + this.getLogin() + "\nRG: " + this.getRg()
				+ "\nTelefone: " + this.getTelefone() + "\nEspecialidades: " + this.getEspecialidades()
				+ this.getEndereco().toString();
	}
	
}
