package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.ConsultaDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.dao.PacienteDao;
import br.com.totvs.clinica.dao.SecretariaDao;

public class Secretaria extends Usuario {
	
	public Secretaria(){}

	public Secretaria(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}

	public int operaSecretaria() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		int op;
		while (loop == true){
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para cadastrar uma nova consulta;");
			System.out.println("Digite 2 para cadastrar um paciente (cadastro b�sico);");
			System.out.println("Digite 3 para cadastrar um paciente (cadastro complementar);");
			System.out.println("Digite 4 para editar um paciente cadastrado;");
			System.out.println("Digite 5 para editar o status de uma consulta marcada;");
			System.out.println("Digite 6 para excluir uma consulta marcada;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			op = sc.nextInt();
			switch (op){
				case 0:
					return 0;
//				case 1:
//					cadastraConsulta();
//					break;
				case 2:
					cadastraPacienteBasico();
					break;
				case 3:
					cadastraPacienteComplementar();
					break;
//				case 4:
//					editaPaciente(getNome());
//					break;
//				case 5:
//					editaStatusConsulta();
//					break;
				case 6:
					excluiConsulta();
					break;
				default:
					System.out.println("Op��o Inv�lida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
		return 0;
	}
	
//	private void cadastraConsulta() {
//		Scanner sc = new Scanner(System.in);
//		Consulta con = new Consulta();
//		System.out.println("Cadastrar Consulta");
//		System.out.println("Digite o nome:");
//		adm.setNome(sc.nextLine());
//		System.out.println("Digite o RG:");
//		adm.setRg(sc.next());
//		System.out.println("Digite o telefone:");
//		adm.setTelefone(sc.next());
//		adm.setEndereco(cadastraEndereco());
//		cadastraLoginSenha(loginSenha, adm);
//		loginSenha.setNivel(1);
//		int op;
//		boolean loop = true;
//		while (loop == true){
//			System.out.println("Confirma o cadastro do Administrador?\n" + adm.toString());
//			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
//			op = sc.nextInt();
//			switch (op){
//				case 0:
//					System.out.println("Opera��o Cancelada!\nUsu�rio n�o cadastrado!");
//					return;
//				case 1:
//					try{
//						LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
//						loginSenhaDao.inserir(loginSenha);
//						AdministradorDao admDao = new AdministradorDao();
//						admDao.inserir(adm);
//					}catch(SQLException e){
//						System.out.println(e.getMessage());
//					}
//						System.out.println("Usu�rio cadastrado com sucesso!");
//					return;
//				default:
//					System.out.println("Op��o Inv�lida!\nTenta novamente.");
//					break;
//			}
//		}
//	}
	
	public void cadastraPacienteBasico(){
		Scanner sc = new Scanner(System.in);
		Paciente pacienteBasico = new Paciente();
		System.out.println("Cadastrar Paciente (cadastro b�sico)");
		System.out.println("Digite o nome:");
		pacienteBasico.setNome(sc.nextLine());
		System.out.println("Digite o telefone:");
		pacienteBasico.setTelefone(sc.next());
		
		int op;
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro b�sico do Paciente?\n" + pacienteBasico.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			op = sc.nextInt();
			switch (op){
				case 0:
					System.out.println("Opera��o Cancelada!\nPaciente n�o cadastrado!");
					return;
				case 1:
					try{
						PacienteDao pacienteDao = new PacienteDao();
						pacienteDao.inserir(pacienteBasico);
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}
						System.out.println("Paciente cadastrado com sucesso!");
					return;
				default:
					System.out.println("Op��o Inv�lida!\nTenta novamente.");
					break;
			}
		}
	}
	
	public void cadastraPacienteComplementar(){
		Scanner sc = new Scanner(System.in);
		Paciente pacienteComplementar = new Paciente();
		System.out.println("Cadastrar Paciente (cadastro complementar)");
		System.out.println("Digite a data de nascimento:");
		pacienteComplementar.setDataNascimento(sc.nextLine());
		pacienteComplementar.setEndereco(cadastraEndereco());
		
		int op;
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro complementar do Paciente?\n" + pacienteComplementar.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			op = sc.nextInt();
			switch (op){
				case 0:
					System.out.println("Opera��o Cancelada!\nPaciente n�o cadastrado!");
					return;
				case 1:
					try{
						PacienteDao pacienteDao = new PacienteDao();
						pacienteDao.inserir(pacienteComplementar);
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}
						System.out.println("Paciente cadastrado com sucesso!");
					return;
				default:
					System.out.println("Op��o Inv�lida!\nTenta novamente.");
					break;
			}
		}
	}
	
//	private void editaPaciente(String nome) {
//		Paciente pac = new Paciente();
//		pac.buscaPaciente(nome);
//		String nome = pac.getNome();
//		Scanner sc = new Scanner(System.in);
//		boolean loop = true;
//		while (loop == true){
//			System.out.println("O que deseja editar no Administrador " + adm.getNome());
//			menuEditar(loginSenha, adm);
//			System.out.println("Deseja alterar mais algum dado?");
//			System.out.println("Digite 1 para SIM ou 0 para N�O!");
//			if (sc.nextInt() == 0)
//				break;
//		}
//		try {
//			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
//			loginSenhaDao.editar(loginSenha, login);
//			AdministradorDao administradorDao = new AdministradorDao();
//			administradorDao.editar(adm, login);
//		} catch(SQLException e){
//		System.out.println(e.getMessage());
//		}
//		System.out.println("Atualiza��o de Administrador realizada com sucesso!");
//		return;
//	}
	
	
	//TESTAR, DESCOBRIR COMO A PESSOA VAI SABER O ID PARA EXCLUIR CONSULTA
	private void excluiConsulta(){
		Scanner sc = new Scanner(System.in);
		Consulta consulta = new Consulta();
		System.out.println("Digite o c�digo da Consulta a ser exclu�da:");
		int id = sc.nextInt();
		try{
			ConsultaDao consultaDao = new ConsultaDao();
			consulta = consultaDao.getPorId(id);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		if(consulta.getCodConsulta() != 0 && consulta.getCodConsulta() == (id) && consulta.getStatusConsulta().equals(StatusConsulta.Agendada)){
			try{
				ConsultaDao consultaDao = new ConsultaDao();
				consultaDao.excluirPorId(id);
				
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
			System.out.println("Consulta exclu�da com sucesso!");
			return;
		} else {
			System.out.println("Consulta inexistente!");
			return;
		}
	}
	
	
	public void buscaSecretaria(LoginSenha loginSenha){
		Secretaria secretaria = new Secretaria();
		try{
			SecretariaDao secretariaDao = new SecretariaDao();
			secretaria = secretariaDao.getPorLogin(loginSenha.getLogin());
		}catch(SQLException e){
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
	
//	public void buscaPaciente(String nome){
//		Secretaria secretaria = new Secretaria();
//		try{
//			SecretariaDao secretariaDao = new SecretariaDao();
//			secretaria = secretariaDao.getPorLogin(loginSenha.getLogin());
//		}catch(SQLException e){
//			System.out.println(e.getMessage());
//		}
//		this.setNome(secretaria.getNome());
//		this.setLogin(secretaria.getLogin());
//		this.setRg(secretaria.getRg());
//		this.setTelefone(secretaria.getTelefone());
//		this.setLogradouro(secretaria.getEndereco().getLogradouro());
//		this.setBairro(secretaria.getEndereco().getBairro());
//		this.setCidade(secretaria.getEndereco().getCidade());
//
//	}
	
	private void menuEditar(Paciente paciente){
		System.out.println("Digite 1 para editar o nome;");
		System.out.println("Digite 2 para editar o telefone;");
		System.out.println("Digite 3 para editar a data de nascimento;");
		System.out.println("Digite 4 para editar o logradouro;");
		System.out.println("Digite 5 para editar o bairro;");
		System.out.println("Digite 6 para editar a cidade;");
		System.out.println("Digite 0 para voltar.");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()){
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
				System.out.println("Op��o Inv�lida!");
				System.out.println("Tente novamente.");
		}
	}
	
	private Endereco cadastraEndereco(){
		Scanner sc = new Scanner(System.in);
		Endereco endereco = new Endereco();
		System.out.println("Endere�o:");
		System.out.println("Digite o logradouro:");
		endereco.setLogradouro(sc.nextLine());
		System.out.println("Digite o bairro:");
		endereco.setBairro(sc.nextLine());
		System.out.println("Digite a cidade:");
		endereco.setCidade(sc.nextLine());
		return endereco;
	}
			
}
