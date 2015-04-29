import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.LoginSenha;

public class Main {

	public void iniciaAdmin(){
		LoginSenha adminLogin = new LoginSenha(1, "admin", "1234", 1);
		
		Administrador admin = new Administrador(1, "administrador", null, null, 1, 0);
		
		LoginSenhaDao loginDao;
		
		try {
			loginDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
			loginDao.inserir(adminLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		LoginSenhaDao usuario;
		LoginSenha loginSenha;
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite o nome do usuário: ");
		String login = sc.next();
		
		usuario = new LoginSenhaDao(new ConnectionProvider().getConnection());
		loginSenha = usuario.getPorUsuario(login);
		
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		
		if (senha.equals(loginSenha.getSenha())){
			System.out.println("Usuario existente no sistema!!!!");
		}
		else{
			System.out.println("Usuário não cadastrado!");
		}
	}
}
