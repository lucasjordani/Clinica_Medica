package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.ConnectionProvider;
import br.com.totvs.clinica.dao.LoginSenhaDao;

public class Administrador extends Usuario {

	public Administrador(){}
	
	public Administrador(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}
	
	public int operaAdministrador() {
		boolean loop = false;
		Scanner sc = new Scanner(System.in);
		int op;
		do{
			System.out.println("O que deseja fazer no sistema?");
			System.out.println("Digite 1 para cadastrar novo usuario;");
			System.out.println("Digite 2 para editar um usu�rio cadastrado;");
			System.out.println("Digite 3 para excluir um usu�rio cadastrado;");
			System.out.println("Digite 0 para se deslogar do sistema.");
			op = sc.nextInt();
			switch (op){
				case 0:
					return 0;
				case 1:
					cadastraUsuario();
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
		}while (loop == false);
		return 0;
	}
	
	public void cadastraUsuario() {
		boolean loop = false;
		Scanner sc = new Scanner(System.in);
		int op;
		do{
			System.out.println("Que tipo de usuario deseja cadastrar?");
			System.out.println("Digite 1 para cadastrar novo administrador;");
			System.out.println("Digite 2 para cadastrar novo m�dico;");
			System.out.println("Digite 3 para cadastrar nova secret�ria;");
			System.out.println("Digite 0 para voltar a tela anterior.");
			op = sc.nextInt();
			switch (op){
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
		}while (loop == false);
	}
	
	private void editaUsuario() {
		System.out.println("Digite o Login do usu�rio a ser editado:");
		Scanner sc = new Scanner(System.in);
		String login = sc.next();
		LoginSenhaDao loginSenhaDao;
		LoginSenha loginSenha = null;
		try{
			loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin("'"+login+"'");
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		do{
			switch (loginSenha.getNivel()){
				case 1:
					editaAdministrador(loginSenha);
					break;
				case 2:
					editaMedico(loginSenha);
					break;
				case 3:
					editaSecretaria(loginSenha);
					break;
				default: 
					System.out.println("Usu�rio inexistente!");
					System.out.println("Tente novamente.");
			}	
		} while (!loginSenha.getLogin().equals(login));
	}

	private void excluiUsuario() {
		
	}
	
	private void editaAdministrador(LoginSenha loginSenha) {
		Administrador adm = new Administrador();
		AdministradorDao admDao;
		try{
			admDao = new AdministradorDao();
			adm.buscaAministrador(loginSenha);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		boolean loop = false;
		Scanner sc = new Scanner(System.in);
		do{
			menuEditar();
			int op = sc.nextInt();
			switch (op){
				case 0:
					return;
				case 1:
					System.out.println("Digite o novo nome:");
					return;
				case 2:
					System.out.println("Digite o novo login:");
					return;
				case 3:
					System.out.println("Digite o novo RG:");
					return;
				case 4:
					System.out.println("Digite o novo telefone:");
					return;
				case 5:
					System.out.println("Digite o novo logradouro:");
					return;
				case 6:
					System.out.println("Digite o novo bairro:");
					return;
				case 7:
					System.out.println("Digite a nova cidade:");
					return;
				case 8:
					System.out.println("Digite a nova senha:");
					return;
				default:
					System.out.println("Op��o Inv�lida!");
					System.out.println("Tente novamente.");
			}
		} while (loop == false);
	}
	
	private void editaMedico(LoginSenha loginSenha) {
		
	}
	
	private void editaSecretaria(LoginSenha loginSenha) {
		
	}

	private void cadastraAministrador() {
		Scanner sc = new Scanner(System.in);
		Administrador adm = new Administrador();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Administrador:");
		System.out.println("Digite o nome:");
		adm.setNome(sc.nextLine());
		System.out.println("Digite o RG:");
		adm.setRg(sc.next());
		System.out.println("Digite o telefone:");
		adm.setTelefone(sc.next());
		Endereco endereco = new Endereco();
		System.out.println("Endere�o:");
		System.out.println("Digite o logradouro:");
		endereco.setLogradouro(sc.next());
		endereco.setLogradouro(sc.nextLine());
		System.out.println("Digite o bairro:");
		endereco.setBairro(sc.nextLine());
		System.out.println("Digite a cidade:");
		endereco.setCidade(sc.nextLine());
		adm.setEndereco(endereco);
		System.out.println("Digite o login:");
		adm.setLogin(sc.next());
		loginSenha.setLogin(adm.getLogin());
		System.out.println("Digite a senha:");
		loginSenha.setSenha(sc.next());
		loginSenha.setNivel(1);
		try{
			AdministradorDao admDao = new AdministradorDao();
			admDao.inserir(adm);
			LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
			loginSenhaDao.inserir(loginSenha);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println("Usu�rio cadastrado com sucesso!");
	}

	private void cadastraMedico() {
		
	}
	
	private void cadastraSecretaria() {
		
	}
	
	public void buscaAministrador(LoginSenha loginSenha) throws SQLException{
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
		Endereco endereco = new Endereco();
		endereco.setLogradouro(administrador.getEndereco().getLogradouro());
		endereco.setBairro(administrador.getEndereco().getBairro());
		endereco.setCidade(administrador.getEndereco().getCidade());
		this.setEndereco(endereco);
	}
	
	private void menuEditar(){
		System.out.println("O que deseja editar no usu�rio?");
		System.out.println("Digite 1 para editar o nome;");
		System.out.println("Digite 2 para editar o login;");
		System.out.println("Digite 3 para editar o RG;");
		System.out.println("Digite 4 para editar o telefone;");
		System.out.println("Digite 5 para editar o logradouro;");
		System.out.println("Digite 6 para editar o bairro;");
		System.out.println("Digite 7 para editar a cidade;");
		System.out.println("Digite 8 para editar a senha;");
		System.out.println("Digite 0 para voltar a tela anterior.");
	}
}
