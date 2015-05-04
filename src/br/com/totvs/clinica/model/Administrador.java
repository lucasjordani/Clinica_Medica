package br.com.totvs.clinica.model;

import java.util.Scanner;

public class Administrador extends Usuario {

	public Administrador(){}
	
	public Administrador(String nome, String login, String rg, String telefone, Endereco endereco) {
		super(nome, login, rg, telefone, endereco);
	}
	
	public int operaAdministrador() {
		Scanner sc = new Scanner(System.in);
		System.out.println("O que deseja fazer no sistema?");
		System.out.println("Digite 1 para cadastradar novo usuario;");
		System.out.println("Digite 2 para editar um usuário cadastrado;");
		System.out.println("Digite 3 para excluir um usuário cadastrado;");
		System.out.println("Digite 0 para se deslogar do sistema.");
		int op = sc.nextInt();
		if (op == 0){
			sc.close();
			return 0;
		}
		else if (op == 1)
			cadastraUsuario();
		else if (op == 2)
			editaUsuario();
		else if (op == 3)
			editaUsuario();
		else {
			System.out.println("Opção Inválida!");
			System.out.println("Tente novamente.");
			operaAdministrador();
		}
		sc.close();
		return 0;
	}
	
	public void cadastraUsuario() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Que tipo de usuario deseja cadastrar?");
		System.out.println("Digite 1 para cadastradar novo administrador;");
		System.out.println("Digite 2 para cadastradar novo médico;");
		System.out.println("Digite 3 para cadastradar nova secretária;");
		System.out.println("Digite 0 para voltar a tela anterior.");
		int op = sc.nextInt();
		if (op == 0)
			cadastraAministrador();
	}
	
	private void cadastraAministrador() {
		// TODO Auto-generated method stub
		
	}

	public void editaUsuario() {
		// TODO Auto-generated method stub
		
	}

	public void exluiUsuario() {
		// TODO Auto-generated method stub
		
	}

	

}
