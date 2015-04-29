import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.LoginSenha;

public class Main {

	public static void iniciaAdmin(){
		LoginSenha adminLogin = new LoginSenha();
		adminLogin.setUsuario("admin");
		adminLogin.setSenha("1234");
		Endereco endereco = new Endereco("Av. Ipiranga", 6681, "Partenon", "Porto Alegre");
		Administrador admin = new Administrador();
		admin.setNome(adminLogin.getUsuario());
		admin.setRg("0123456789");
		admin.setTelefone("00000000");
		admin.setEndereco(endereco);
		
		LoginSenhaDao loginDao;
		
		try {
			loginDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
			loginDao.inserir(adminLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {


//		LoginSenhaDao usuario;
//		LoginSenha loginSenha;
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("Digite o nome do usuário: ");
//		String login = sc.next();
//		
//		usuario = new LoginSenhaDao(new ConnectionProvider().getConnection());
//		loginSenha = usuario.getPorUsuario("'"+login+"'");
//		
//		System.out.println("Digite a senha: ");
//		String senha = sc.next();
//		
//		if (senha.equals(loginSenha.getSenha())){
//			System.out.println("Usuario existente no sistema!!!!");
//		}
//		else{
//			System.out.println("Usuário não cadastrado!");
//		}
	}
}
