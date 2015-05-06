package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.dao.MedicoDao;
import br.com.totvs.clinica.dao.SecretariaDao;

public class Administrador extends Usuario {

	public Administrador(){}
	
	public Administrador(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}
	
	public int operaAdministrador() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop == true){
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para cadastrar novo usuario;");
			System.out.println("Digite 2 para editar um usu�rio cadastrado;");
			System.out.println("Digite 3 para excluir um usu�rio cadastrado;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			switch (sc.nextInt()){
				case 0:
					return 0;
				case 1:
					cadastrar();
					break;
				case 2:
					editaUsuario();
					break;
				case 3:
					excluiUsuario();
					break;
				default:
					System.out.println("Op��o Inv�lida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
		return 0;
	}
	
	
	private void cadastrar() {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true){
			System.out.println("Que tipo de usuario deseja cadastrar?");
			System.out.println("Digite 1 para cadastrar novo administrador;");
			System.out.println("Digite 2 para cadastrar novo m�dico;");
			System.out.println("Digite 3 para cadastrar nova secret�ria;");
			System.out.println("Digite 0 para voltar a tela anterior.");
			switch (sc.nextInt()){
				case 0:
					return;
				case 1:
					cadastraAministrador();
					break;
				case 2:
					cadastraMedico();
					break;
				case 3:
					cadastraSecretaria();
					break;
				default:
					System.out.println("Op��o Inv�lida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
	}
	
	private void editaUsuario() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o Login do usu�rio a ser editado:");
		String login = sc.next();
		LoginSenha loginSenha = null;
		try{
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin(login);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		if (loginSenha.getLogin() != null && loginSenha.getLogin().equals(login)){
			switch (loginSenha.getNivel()){
				case 1:
					editaAdministrador(loginSenha);
					return;
				case 2:
					editaMedico(loginSenha);
					return;
				case 3:
					editaSecretaria(loginSenha);
					return;
			}
		} else{
			System.out.println("Login inexistente!");
			return;
		}
	}

	private void excluiUsuario(){
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop == true){
			System.out.println("Que tipo de usuario deseja excluir?");
			System.out.println("Digite 1 para excluir administrador;");
			System.out.println("Digite 2 para excluir m�dico;");
			System.out.println("Digite 3 para excluir secret�ria;");
			System.out.println("Digite 0 para voltar a tela anterior.");
			switch (sc.nextInt()){
				case 0:
					return;
				case 1:
					excluiAdministrador();
					return;
				case 2:
					excluiMedico();
					return;
				case 3:
					excluiSecretaria();
					return;
				default:
					System.out.println("Op��o Inv�lida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
	}
	
	private void cadastraAministrador() {
		Scanner sc = new Scanner(System.in);
		Administrador adm = new Administrador();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Administrador");
		cadastraUsuario(adm);
		cadastraEndereco(adm);
		cadastraLoginSenha(loginSenha, adm);
		loginSenha.setNivel(1);
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro do Administrador?\n" + adm.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			switch (sc.nextInt()){
				case 0:
					System.out.println("Opera��o Cancelada!\nUsu�rio n�o cadastrado!");
					return;
				case 1:
					try{
						LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
						loginSenhaDao.inserir(loginSenha);
						AdministradorDao admDao = new AdministradorDao();
						admDao.inserir(adm);
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}
						System.out.println("Usu�rio cadastrado com sucesso!");
					return;
				default:
					System.out.println("Op��o Inv�lida!\nTenta novamente.");
					break;
			}
		}
	}

	private void cadastraMedico() {
		Scanner sc = new Scanner(System.in);
		Medico medico = new Medico();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar M�dico");
		cadastraUsuario(medico);
		cadastraEndereco(medico);
		System.out.println("Digite as especialidades do m�dico:");
		medico.setEspecialidades(sc.next() + sc.nextLine());
		cadastraLoginSenha(loginSenha, medico);
		loginSenha.setNivel(2);
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro do M�dico?\n" + medico.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			switch (sc.nextInt()){
				case 0:
					System.out.println("Opera��o Cancelada!\nM�dico n�o cadastrado!");
					return;
				case 1:
					try{
						LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
						loginSenhaDao.inserir(loginSenha);
						MedicoDao medicoDao = new MedicoDao();
						medicoDao.inserir(medico);
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}
						System.out.println("Usu�rio cadastrado com sucesso!");
					return;
				default:
					System.out.println("Op��o Inv�lida!\nTenta novamente.");
					
					break;
			}
		}
	}
	
	private void cadastraSecretaria() {
		Scanner sc = new Scanner(System.in);
		Secretaria secretaria = new Secretaria();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Secret�ria");
		cadastraUsuario(secretaria);
		cadastraEndereco(secretaria);
		cadastraLoginSenha(loginSenha, secretaria);
		loginSenha.setNivel(3);
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro da Secret�ria?\n" + secretaria.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			switch (sc.nextInt()){
				case 0:
					System.out.println("Opera��o Cancelada!\nUsu�rio n�o cadastrado!");
					return;
				case 1:
					try{
						LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
						loginSenhaDao.inserir(loginSenha);
						SecretariaDao secretariaDao = new SecretariaDao();
						secretariaDao.inserir(secretaria);
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}
						System.out.println("Usu�rio cadastrado com sucesso!");
					return;
				default:
					System.out.println("Op��o Inv�lida!\nTenta novamente.");
					break;
			}
		}
	}
	
	private void editaAdministrador(LoginSenha loginSenha) {
		Administrador adm = new Administrador();
		adm.buscaAministrador(loginSenha);
		String login = adm.getLogin();
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true){
			System.out.println("O que deseja editar no Administrador " + adm.getNome());
			menuEditar(loginSenha, adm);
			System.out.println("Deseja alterar mais algum dado?");
			System.out.println("Digite 1 para SIM ou 0 para N�O!");
			if (sc.nextInt() == 0)
				break;
		}
		try {
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenhaDao.editar(loginSenha, login);
			AdministradorDao administradorDao = new AdministradorDao();
			administradorDao.editar(adm, login);
		} catch(SQLException e){
		System.out.println(e.getMessage());
		}
		System.out.println("Atualiza��o de Administrador realizada com sucesso!");
		return;
	}
	
	private void editaMedico(LoginSenha loginSenha) {
		Medico medico = new Medico();
		medico.buscaMedico(loginSenha);
		String login = medico.getLogin();
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true){
			System.out.println("O que deseja editar no M�dico " + medico.getNome());
			menuEditarMedico(loginSenha, medico, medico);
			System.out.println("Deseja alterar mais algum dado?");
			System.out.println("Digite 1 para SIM ou 0 para N�O!");
			if (sc.nextInt() == 0)
				break;
		}
		try {
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenhaDao.editar(loginSenha, login);
			MedicoDao medicoDao = new MedicoDao();
			medicoDao.editar(medico, login);
		} catch(SQLException e){
		System.out.println(e.getMessage());
		}
		System.out.println("Atualiza��o de M�dico realizada com sucesso!");
		return;
	}
	
	
	private void editaSecretaria(LoginSenha loginSenha) {
		Secretaria secretaria = new Secretaria();
		secretaria.buscaSecretaria(loginSenha);
		String login = secretaria.getLogin();
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true){
			System.out.println("O que deseja editar no Secretaria " + secretaria.getNome());
			menuEditar(loginSenha, secretaria);
			System.out.println("Deseja alterar mais algum dado?");
			System.out.println("Digite 1 para SIM ou 0 para N�O!");
			if (sc.nextInt() == 0)
				break;
		}
		try {
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenhaDao.editar(loginSenha, login);
			SecretariaDao secretariaDao = new SecretariaDao();
			secretariaDao.editar(secretaria, login);
		} catch(SQLException e){
		System.out.println(e.getMessage());
		}
		System.out.println("Atualiza��o de Secretaria realizada com sucesso!");
		return;
	}
	
	private void excluiAdministrador(){
		Scanner sc = new Scanner(System.in);
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Digite o login do Administrador a ser exclu�do:");
		String login = sc.next();
		try{
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin(login);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		if(loginSenha.getLogin() != null && loginSenha.getLogin().equals(login)){
			try{
				AdministradorDao admDao = new AdministradorDao();
				admDao.excluirPorLogin(login);
				LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
				loginSenhaDao.excluirPorLogin(login);
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
			System.out.println("Administrador exclu�do com sucesso!");
			return;
		} else {
			System.out.println("Login inexistente!");
			return;
		}
	}
	
	private void excluiMedico(){
		Scanner sc = new Scanner(System.in);
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Digite o login do M�dico a ser exclu�do:");
		String login = sc.next();
		try{
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin(login);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		if(loginSenha.getLogin() != null && loginSenha.getLogin().equals(login)){
			try{
				MedicoDao medicoDao = new MedicoDao();
				medicoDao.excluirPorLogin(login);
				LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
				loginSenhaDao.excluirPorLogin(login);
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
			System.out.println("M�dico exclu�do com sucesso!");
			return;
		} else {
			System.out.println("Login inexistente!");
			return;
		}
	}
	
	private void excluiSecretaria(){
		Scanner sc = new Scanner(System.in);
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Digite o login do Secret�ria a ser exclu�do:");
		String login = sc.next();
		try{
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin(login);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		if(loginSenha.getLogin() != null && loginSenha.getLogin().equals(login)){
			try{
				SecretariaDao secretariaDao = new SecretariaDao();
				secretariaDao.excluirPorLogin(login);
				LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
				loginSenhaDao.excluirPorLogin(login);
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
			System.out.println("Secret�ria exclu�da com sucesso!");
			return;
		} else {
			System.out.println("Login inexistente!");
			return;
		}
	}
	
	public void buscaAministrador(LoginSenha loginSenha){
		Administrador administrador = new Administrador();
		try{
			AdministradorDao admDao = new AdministradorDao();
			administrador = admDao.getPorLogin(loginSenha.getLogin());
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		this.setNome(administrador.getNome());
		this.setLogin(administrador.getLogin());
		this.setRg(administrador.getRg());
		this.setTelefone(administrador.getTelefone());
		this.setLogradouro(administrador.getEndereco().getLogradouro());
		this.setBairro(administrador.getEndereco().getBairro());
		this.setCidade(administrador.getEndereco().getCidade());
	}
	
	private void menuEditar(LoginSenha loginSenha, Usuario usuario){
		System.out.println("Digite 1 para editar o nome;");
		System.out.println("Digite 2 para editar o login;");
		System.out.println("Digite 3 para editar o RG;");
		System.out.println("Digite 4 para editar o telefone;");
		System.out.println("Digite 5 para editar o logradouro;");
		System.out.println("Digite 6 para editar o bairro;");
		System.out.println("Digite 7 para editar a cidade;");
		System.out.println("Digite 8 para editar a senha;");
		System.out.println("Digite 0 para voltar.");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()){
			case 0:
				return;
			case 1:
				System.out.println("Digite o novo nome:");
				usuario.setNome((sc.next() + sc.nextLine()));
				System.out.println(usuario.getNome());
				break;
			case 2:
				System.out.println("Digite o novo login:");
				usuario.setLogin(sc.next());
				loginSenha.setLogin(usuario.getLogin());
				break;
			case 3:
				System.out.println("Digite o novo RG:");
				usuario.setRg(sc.next());
				break;
			case 4:
				System.out.println("Digite o novo telefone:");
				usuario.setTelefone(sc.next());
				break;
			case 5:
				System.out.println("Digite o novo logradouro:");
				usuario.setLogradouro(sc.next() + sc.nextLine());
				break;
			case 6:
				System.out.println("Digite o novo bairro:");
				usuario.setBairro(sc.next() + sc.nextLine());
				break;
			case 7:
				System.out.println("Digite a nova cidade:");
				usuario.setCidade(sc.next() + sc.nextLine());
				break;
			case 8:
				System.out.println("Digite a nova senha:");
				loginSenha.setSenha(sc.next());
				break;
			default:
				System.out.println("Op��o Inv�lida!");
				System.out.println("Tente novamente.");
		}
	}
	
	private void menuEditarMedico(LoginSenha loginSenha, Usuario usuario, Medico medico){
		System.out.println("Digite 1 para editar o nome;");
		System.out.println("Digite 2 para editar o login;");
		System.out.println("Digite 3 para editar o RG;");
		System.out.println("Digite 4 para editar o telefone;");
		System.out.println("Digite 5 para editar o logradouro;");
		System.out.println("Digite 6 para editar o bairro;");
		System.out.println("Digite 7 para editar a cidade;");
		System.out.println("Digite 8 para editar a especialidade;");
		System.out.println("Digite 9 para editar a senha;");
		System.out.println("Digite 0 para voltar.");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()){
			case 0:
				return;
			case 1:
				System.out.println("Digite o novo nome:");
				usuario.setNome((sc.next() + sc.nextLine()));
				System.out.println(usuario.getNome());
				break;
			case 2:
				System.out.println("Digite o novo login:");
				usuario.setLogin(sc.next());
				loginSenha.setLogin(usuario.getLogin());
				break;
			case 3:
				System.out.println("Digite o novo RG:");
				usuario.setRg(sc.next());
				break;
			case 4:
				System.out.println("Digite o novo telefone:");
				usuario.setTelefone(sc.next());
				break;
			case 5:
				System.out.println("Digite o novo logradouro:");
				usuario.setLogradouro(sc.next() + sc.nextLine());
				break;
			case 6:
				System.out.println("Digite o novo bairro:");
				usuario.setBairro(sc.next() + sc.nextLine());
				break;
			case 7:
				System.out.println("Digite a nova cidade:");
				usuario.setCidade(sc.next() + sc.nextLine());
				break;
			case 8:
				System.out.println("Digite a nova especialidade:");
				medico.setEspecialidades(sc.next());
				break;
			case 9:
				System.out.println("Digite a nova senha:");
				loginSenha.setSenha(sc.next());
				break;
			default:
				System.out.println("Op��o Inv�lida!");
				System.out.println("Tente novamente.");
		}
	}

	private void cadastraUsuario(Usuario usuario){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome:");
		usuario.setNome(sc.nextLine());
		System.out.println("Digite o RG:");
		usuario.setRg(sc.next());
		System.out.println("Digite o telefone:");
		usuario.setTelefone(sc.next());
	}
	
	private void cadastraEndereco(Usuario usuario){
		usuario.setEndereco(new Endereco());
		Scanner sc = new Scanner(System.in);
		System.out.println("Endere�o:");
		System.out.println("Digite o logradouro:");
		usuario.setLogradouro(sc.nextLine());
		System.out.println("Digite o bairro:");
		usuario.setBairro(sc.nextLine());
		System.out.println("Digite a cidade:");
		usuario.setCidade(sc.nextLine());
	}

	private void cadastraLoginSenha(LoginSenha loginSenha, Usuario usuario){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o login:");
		usuario.setLogin(sc.next());
		loginSenha.setLogin(usuario.getLogin());
		System.out.println("Digite a senha:");
		loginSenha.setSenha(sc.next());
	}

}
