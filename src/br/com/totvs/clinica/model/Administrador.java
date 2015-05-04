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
			System.out.println("Digite 2 para editar um usuário cadastrado;");
			System.out.println("Digite 3 para excluir um usuário cadastrado;");
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
					exluiUsuario();
					break;
				default:
					System.out.println("Opção Inválida!");
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
			System.out.println("Digite 1 para cadastradar novo administrador;");
			System.out.println("Digite 2 para cadastradar novo médico;");
			System.out.println("Digite 3 para cadastradar nova secretária;");
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
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
					break;
			}
		}while (loop == false);
	}
	
	private void editaUsuario() {
		System.out.println("Digite o Login do usuário a ser editado:");
		Scanner sc = new Scanner(System.in);
		String login = sc.next();
		LoginSenhaDao loginSenhaDao;
		LoginSenha loginSenha = null;
		try{
			loginSenhaDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
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
					System.out.println("Usuário inexistente!");
					System.out.println("Tente novamente.");
			}	
		} while (!loginSenha.getLogin().equals(login));
	}

	private void exluiUsuario() {
		
	}
	
	private void editaAdministrador(LoginSenha loginSenha) {
		Administrador adm = new Administrador();
		AdministradorDao admDao;
		try{
			admDao = new AdministradorDao(new ConnectionProvider().getConnection());
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
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
			}
		} while (loop == false);
	}
	
	private void editaMedico(LoginSenha loginSenha) {
		
	}
	
	private void editaSecretaria(LoginSenha loginSenha) {
		
	}

	private void cadastraAministrador() {
		System.out.println("Digite o nome do usuário"); 
	}

	private void cadastraMedico() {
		
	}
	
	private void cadastraSecretaria() {
		
	}
	
	public void buscaAministrador(LoginSenha loginSenha) throws SQLException{
		Administrador administrador = new Administrador();
		try{
			AdministradorDao admDao = new AdministradorDao(new ConnectionProvider().getConnection());
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
		System.out.println("O que deseja editar no usuário?");
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
