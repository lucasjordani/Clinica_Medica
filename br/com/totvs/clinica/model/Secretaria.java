package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.totvs.clinica.dao.ConsultaDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.dao.MedicoDao;
import br.com.totvs.clinica.dao.PacienteDao;
import br.com.totvs.clinica.dao.SecretariaDao;

public class Secretaria extends Usuario {

	public Secretaria() {
	}

	public Secretaria(String nome, String login, String rg, String telefone,
			Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}

	public int operaSecretaria() throws SQLException {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true) {
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para cadastrar uma nova consulta;");
			System.out.println("Digite 2 para cadastrar um paciente;");
			System.out.println("Digite 3 para editar um paciente cadastrado;");
			System.out.println("Digite 4 para editar uma consulta marcada;");
			System.out.println("Digite 5 para excluir uma consulta marcada;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			switch (sc.nextInt()) {
			case 0:
				return 0;
			case 1:
				cadastraConsulta();
				break;
			case 2:
				cadastraPaciente();
				break;
			 case 3:
			 editaPaciente();
			 break;
			// case 4:
			// editaConsulta();
			// break;
			case 5:
				excluiConsulta();
				break;
			default:
				System.out.println("Opção Inválida!");
				System.out.println("Tente novamente.");
				break;
			}
		}
		return 0;
	}
	
	private void cadastraConsulta() {

		Scanner sc = new Scanner(System.in);
		Consulta consulta = new Consulta();
		System.out.println("Cadastro de Consulta:");
		System.out.println("Digite o nome do Paciente que irá consultar:");
		String nomePaciente = sc.next() + sc.nextLine();

		Paciente paciente = new Paciente();
		try {
			PacienteDao pacienteDao = new PacienteDao();
			paciente = pacienteDao.getPorNome(nomePaciente);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (paciente.getNome() != null && paciente.getNome().equals(nomePaciente)) {
			System.out.println("Digite o nome do Médico responsável pela consulta:");
			String nomeMedico = sc.next() + sc.nextLine();
			Medico medico = new Medico();
			try {
				MedicoDao medicoDao = new MedicoDao();
				medico = medicoDao.getPorNome(nomeMedico);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			if (medico.getNome() != null && medico.getNome().equals(nomeMedico)) {
				consulta.setPaciente(nomePaciente);
				consulta.setMedico(medico.getLogin());
				System.out.println("Digite o plano de saúde do Paciente:");
				consulta.setPlanoSaude(sc.next() + sc.nextLine());
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
				System.out.println("Digite a data da consulta (exemplo: 10/05/2014):");
				String data = sc.next() + sc.nextLine();
				System.out.println("Digite a hora da consulta (exemplo: 15:30):");
				String hora = sc.next() + sc.nextLine();
				Date dataHoraFormatada = null;
				try {
					dataHoraFormatada = fmt.parse(data + " - " + hora);
				} catch (ParseException e) {
					e.getMessage();
				}
				consulta.setDataHora(fmt.format(dataHoraFormatada));
				consulta.setStatusConsulta((StatusConsulta.AGENDADA));
				System.out.println("Confirma o cadastro da Consulta?"
						+ consulta.toString());
			} else {
				System.out.println("Médico não cadastrado!");
				return;
			}
			if (paciente.getNome() != null
					&& paciente.getNome().equals(nomePaciente)) {
				consulta.setPaciente(nomePaciente);
				consulta.setMedico(nomeMedico);
				System.out.println("Digite o plano de saúde do Paciente:");
				consulta.setPlanoSaude(sc.next() + sc.nextLine());
				System.out.println("Digite a data e a hora da consulta:");
				consulta.setDataHora(sc.next() + sc.nextLine());
				consulta.setStatusConsulta((StatusConsulta.AGENDADA));
				System.out.println("Confirma o cadastro da Consulta?"
						+ consulta.toString());
			} else {
				System.out.println("Paciente não cadastrado no sistema!");
				cadastraPaciente();
				return;
			}
			boolean loop = true;
			while (loop == true) {
				System.out
						.println("Digite 1 para confirmar ou 0 para cancelar.");
				switch (sc.nextInt()) {
				case 0:
					System.out
							.println("Operação Cancelada!\nConsulta não cadastrada!");
					return;
				case 1:
					try {
						ConsultaDao consultaDao = new ConsultaDao();
						consultaDao.inserir(consulta);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("Consulta cadastrada com sucesso!");
					return;
				default:
					System.out.println("Opção Inválida!\nTente novamente.");
					break;
				}

			}
		}
	}


	private void cadastraPaciente() {

		Scanner sc = new Scanner(System.in);
		Paciente paciente = new Paciente();
		System.out.println("Cadastrar Paciente:");
		System.out.println("Digite o nome do paciente:");
		String nome = sc.next() + sc.nextLine();
		try {
			PacienteDao pacienteDao = new PacienteDao();
			paciente = pacienteDao.getPorNome(nome);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (!(paciente.getNome() != null && paciente.getNome().equals(nome))) {
			paciente.setNome(nome);
			System.out.println("Digite o telefone:");
			paciente.setTelefone(sc.next());
		}

		System.out.println("Deseja cadastrar a data de nascimento?");
		System.out.println("Digite 1 para SIM e 0 para NÃO!");
		if(sc.nextInt() == 1){
			System.out.println("Digite a data de nascimento:");
			System.out.println("(Exemplo: 15/12/1986)");
			String dataNascimento = sc.next() + sc.nextLine();
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = null;
			try{
				dataFormatada = fmt.parse(dataNascimento);
			} catch(ParseException e){
				e.getMessage();
			}
			paciente.setDataNascimento(fmt.format(dataFormatada));
		}

		System.out.println("Deseja cadastrar o endereço?");
		System.out.println("Digite 1 para SIM e 0 para NÃO!");
		if (sc.nextInt() == 1)
			paciente.setEndereco(cadastraEndereco());
		else
			paciente.setEndereco(new Endereco());
		System.out.println("Confirma o cadastro do Paciente?"
				+ paciente.toString());
		boolean loop = true;
		while (loop == true) {
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");

			
			switch (sc.nextInt()){
				case 0:
					System.out.println("Operação Cancelada!\nPaciente não cadastrado!");
					return;
				case 1:
					PacienteDao pacienteDao;
					try{
						pacienteDao = new PacienteDao();
						pacienteDao.inserir(paciente);
					}catch(SQLException e){
						System.out.println("Paciente já cadastrado, atualizando os dados!");
					}
					try{
						pacienteDao = new PacienteDao();
						pacienteDao.editar(paciente);
					} catch(SQLException e){
						System.out.println(e.getMessage());
					}
					System.out.println("Paciente cadastrado com sucesso!");
					return;
				default:
					System.out.println("Opção Inválida!\nTente novamente.");
			

			}
		}
	}

	private void editaPaciente(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome do Paciente a ser editado:");
		String nome = sc.next() + sc.nextLine();
		Paciente paciente = new Paciente();
		paciente.buscaPaciente(nome);
		
		try{
			PacienteDao pacienteDao = new PacienteDao();
			paciente = pacienteDao.getPorNome(nome);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		boolean loop = true;
		while (loop == true){
			if (paciente.getNome() != null && paciente.getNome().equals(nome)){
				System.out.println("O que deseja editar no Paciente " + paciente.getNome());
				menuEditar(paciente);
				System.out.println(paciente.toString());
				System.out.println("Deseja alterar mais algum dado?");
				System.out.println("Digite 1 para SIM ou 0 para NÃO!");
				
				if (sc.nextInt() == 0)
					break;
			} else {
				System.out.println("Paciente não cadastrado no sistema!");
				cadastraPaciente();
				return;
			}
		}
		
		try {
			PacienteDao pacienteDao = new PacienteDao();
			pacienteDao.editar(paciente);
		} catch(SQLException e){
		System.out.println(e.getMessage());
		}
		System.out.println("Atualização de Paciente realizada com sucesso!");
		return;
	}
	
	
	// EDITAR STATUS CONSULTA

	// FALTA FAZER APARECER A LISTA DAS CONSULTAS
	private void excluiConsulta() {
		Scanner sc = new Scanner(System.in);
		Consulta consulta = new Consulta();
		List<Consulta> consultas = new ArrayList<>();
		System.out.println("Lista de todas as Consultas:");
		try {
			ConsultaDao consultaDao = new ConsultaDao();
			consultas = consultaDao.getTodos();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		for (Consulta cons : consultas)
			cons.toStringExcluir();
		System.out.println("Digite o código da Consulta a ser excluída:");
		int id = sc.nextInt();
		try {
			ConsultaDao consultaDao = new ConsultaDao();
			consulta = consultaDao.getPorId(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (consulta.getCodConsulta() != 0 && consulta.getCodConsulta() == (id)
				&& consulta.getStatusConsulta().equals(StatusConsulta.AGENDADA)) {
			try {
				ConsultaDao consultaDao = new ConsultaDao();
				consultaDao.excluirPorId(id);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Consulta excluída com sucesso!");
			return;
		} else {
			System.out.println("Consulta inexistente!");
			return;
		}
	}

	public void buscaSecretaria(LoginSenha loginSenha) {
		Secretaria secretaria = new Secretaria();
		try {
			SecretariaDao secretariaDao = new SecretariaDao();
			secretaria = secretariaDao.getPorLogin(loginSenha.getLogin());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		this.setNome(secretaria.getNome());
		this.setLogin(secretaria.getLogin());
		this.setRg(secretaria.getRg());
		this.setTelefone(secretaria.getTelefone());
		this.setLogradouro(secretaria.getEndereco().getLogradouro());
		this.setBairro(secretaria.getEndereco().getBairro());
		this.setCidade(secretaria.getEndereco().getCidade());

	}

	private void menuEditar(Paciente paciente) {
		System.out.println("Digite 1 para editar o nome;");
		System.out.println("Digite 2 para editar o telefone;");
		System.out.println("Digite 3 para editar a data de nascimento;");
		System.out.println("Digite 4 para editar o logradouro;");
		System.out.println("Digite 5 para editar o bairro;");
		System.out.println("Digite 6 para editar a cidade;");
		System.out.println("Digite 0 para voltar.");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 0:
			return;
		case 1:
			System.out.println("Digite o novo nome:");
			paciente.setNome((sc.next() + sc.nextLine()));
			System.out.println(paciente.getNome());
			break;
		case 2:
			System.out.println("Digite o novo telefone:");
			paciente.setTelefone(sc.next());
			break;
		case 3:
			System.out.println("Digite a nova data de nascimento:");
			paciente.setDataNascimento(sc.next());
			break;
		case 4:
			System.out.println("Digite o novo logradouro:");
			paciente.setLogradouro(sc.next() + sc.nextLine());
			break;
		case 5:
			System.out.println("Digite o novo bairro:");
			paciente.setBairro(sc.next() + sc.nextLine());
			break;
		case 6:
			System.out.println("Digite a nova cidade:");
			paciente.setCidade(sc.next() + sc.nextLine());
			break;

		default:
			System.out.println("Opção Inválida!");
			System.out.println("Tente novamente.");
		}
	}

	private Endereco cadastraEndereco() {
//		
//		new java.sql.Date(new java.util.Date(2013,12,01).getTime());
		
		Scanner sc = new Scanner(System.in);
		Endereco endereco = new Endereco();
		System.out.println("Endereço:");
		System.out.println("Digite o logradouro:");
		endereco.setLogradouro(sc.nextLine());
		System.out.println("Digite o bairro:");
		endereco.setBairro(sc.nextLine());
		System.out.println("Digite a cidade:");
		endereco.setCidade(sc.nextLine());
		return endereco;
	}

}
