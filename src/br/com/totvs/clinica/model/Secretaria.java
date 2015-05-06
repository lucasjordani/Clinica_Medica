package br.com.totvs.clinica.model;

import java.sql.SQLException;
import java.util.Scanner;

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
			System.out.println("Digite 2 para cadastrar um paciente (cadastro básico);");
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
//					cadastrar();
//					break;
//				case 2:
//					editaUsuario();
//					break;
//				case 3:
//					excluiUsuario();
//					break;
//				case 4:
//					excluiUsuario();
//					break;
//				case 5:
//					excluiUsuario();
//					break;
//				case 6:
//					excluiUsuario();
//					break;
				default:
					System.out.println("Opção Inválida!");
					System.out.println("Tente novamente.");
					break;
			}
		}
		return 0;
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
		Endereco endereco = new Endereco();
		endereco.setLogradouro(secretaria.getEndereco().getLogradouro());
		endereco.setBairro(secretaria.getEndereco().getBairro());
		endereco.setCidade(secretaria.getEndereco().getCidade());
		this.setEndereco(endereco);
	}
			
}
