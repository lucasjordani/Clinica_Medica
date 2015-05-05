package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.ConnectionProvider;
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
		int op;
		while (loop == true){
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
					cadastrar();
					break;
				case 2:
					editaUsuario();
					break;
				case 3:
					excluiUsuario();
					break;
				default:
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
		return 0;
	}
	
	private void cadastrar() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		int op;
		while (loop == true){
			System.out.println("Que tipo de usuario deseja cadastrar?");
			System.out.println("Digite 1 para cadastrar novo administrador;");
			System.out.println("Digite 2 para cadastrar novo médico;");
			System.out.println("Digite 3 para cadastrar nova secretária;");
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
		}
	}
	
	private void editaUsuario() {
		System.out.println("Digite o Login do usuário a ser editado:");
		Scanner sc = new Scanner(System.in);
		String login = sc.next();
		LoginSenhaDao loginSenhaDao;
		LoginSenha loginSenha = null;
		try{
			loginSenhaDao = new LoginSenhaDao();
			loginSenha = loginSenhaDao.getPorLogin(login);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		do{
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
				default: 
					System.out.println("Usuário inexistente!");
					System.out.println("Tente novamente.");
					break;
			}	
		} while (!loginSenha.getLogin().equals(login));
	}

	private void excluiUsuario() {
		
	}
	
	private void cadastraAministrador() {
		Scanner sc = new Scanner(System.in);
		Administrador adm = new Administrador();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Administrador");
		System.out.println("Digite o nome:");
		adm.setNome(sc.nextLine());
		System.out.println("Digite o RG:");
		adm.setRg(sc.next());
		System.out.println("Digite o telefone:");
		adm.setTelefone(sc.next());
		adm.setEndereco(cadastraEndereco());
		cadastraLoginSenha(loginSenha, adm);
		loginSenha.setNivel(1);
		int op;
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro do Administrador?\n" + adm.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			op = sc.nextInt();
			switch (op){
				case 0:
					System.out.println("Operação Cancelada!\nUsuário não cadastrado!");
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
						System.out.println("Usuário cadastrado com sucesso!");
					return;
				default:
					System.out.println("Opção Inválida!\nTenta novamente.");
					break;
			}
		}
	}

	private void cadastraMedico() {
		Scanner sc = new Scanner(System.in);
		Medico medico = new Medico();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Médico");
		System.out.println("Digite o nome:");
		medico.setNome(sc.nextLine());
		System.out.println("Digite o RG:");
		medico.setRg(sc.next());
		System.out.println("Digite o telefone:");
		medico.setTelefone(sc.next());
		medico.setEndereco(cadastraEndereco());
		System.out.println("Digite as especialidades do médico:");
		medico.setEspecialidades(sc.next() + sc.nextLine());
		cadastraLoginSenha(loginSenha, medico);
		loginSenha.setNivel(2);
		int op;
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro do Médico?\n" + medico.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			op = sc.nextInt();
			switch (op){
				case 0:
					System.out.println("Operação Cancelada!\nMédico não cadastrado!");
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
						System.out.println("Usuário cadastrado com sucesso!");
					return;
				default:
					System.out.println("Opção Inválida!\nTenta novamente.");
					break;
			}
		}
	}
	
	private void cadastraSecretaria() {
		Scanner sc = new Scanner(System.in);
		Secretaria secretaria = new Secretaria();
		LoginSenha loginSenha = new LoginSenha();
		System.out.println("Cadastrar Secretária");
		System.out.println("Digite o nome:");
		secretaria.setNome(sc.nextLine());
		System.out.println("Digite o RG:");
		secretaria.setRg(sc.next());
		System.out.println("Digite o telefone:");
		secretaria.setTelefone(sc.next());
		secretaria.setEndereco(cadastraEndereco());
		cadastraLoginSenha(loginSenha, secretaria);
		loginSenha.setNivel(3);
		int op;
		boolean loop = true;
		while (loop == true){
			System.out.println("Confirma o cadastro da Secretária?\n" + secretaria.toString());
			System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
			op = sc.nextInt();
			switch (op){
				case 0:
					System.out.println("Operação Cancelada!\nUsuário não cadastrado!");
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
						System.out.println("Usuário cadastrado com sucesso!");
					return;
				default:
					System.out.println("Opção Inválida!\nTenta novamente.");
					break;
			}
		}
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
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
			}
		} while (loop == false);
	}
	
	private void editaMedico(LoginSenha loginSenha) {
		
	}
	
	private void editaSecretaria(LoginSenha loginSenha) {
		
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

	private Endereco cadastraEndereco(){
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

	private void cadastraLoginSenha(LoginSenha loginSenha, Usuario usuario){
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o login:");
		usuario.setLogin(sc.next());
		loginSenha.setLogin(usuario.getLogin());
		System.out.println("Digite a senha:");
		loginSenha.setSenha(sc.next());
	}

}
