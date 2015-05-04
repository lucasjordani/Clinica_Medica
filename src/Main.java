import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.LoginSenha;
import br.com.totvs.clinica.model.Medico;
import br.com.totvs.clinica.model.Secretaria;
import br.com.totvs.clinica.model.Usuario;

public class Main {

	public static void iniciaAdmin(){
		
		LoginSenha adminLogin = new LoginSenha();
		adminLogin.setLogin("admin");
		adminLogin.setSenha("1234");
		adminLogin.setNivel(1);
		Endereco endereco = new Endereco("Av. Ipiranga, 6681", "Partenon", "Porto Alegre");
		Administrador admin = new Administrador();
		admin.setNome("administrador");
		admin.setLogin("admin");
		admin.setRg("0123456789");
		admin.setTelefone("00000000");
		admin.setEndereco(endereco);
		
		AdministradorDao adminDao;
		LoginSenhaDao loginDao;
		
		try {
			loginDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
			loginDao.inserir(adminLogin);
			adminDao = new AdministradorDao(new ConnectionProvider().getConnection());
			adminDao.inserir(admin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void validaUsuario() throws SQLException{
		
		LoginSenhaDao loginSenhaDao;
		LoginSenha loginSenha;
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome do usu�rio: ");
		String login = sc.next();
		
		loginSenhaDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
		loginSenha = loginSenhaDao.getPorLogin("'"+login+"'");
		
		if (loginSenha.getLogin() != null)
		{
			System.out.println("Digite a senha: ");
			String senha = sc.next();
			
			if (senha.equals(loginSenha.getSenha())){
				if (loginSenha.getNivel() == 1){
					Administrador adm = buscaAministrador(loginSenha);
					System.out.println("Seja Bem-Vindo "+ adm.getNome());
				} else if (loginSenha.getNivel() == 2){
					Medico medico = new Medico();
				} else{
					Secretaria secretaria = new Secretaria();
				}

				
			}else{
				System.out.println("Senha inv�lida!");
				validaUsuario();
			}
		}else{
			System.out.println("Usu�rio n�o cadastrado!");
			validaUsuario();
		}

	}
	
	public static Administrador buscaAministrador(LoginSenha loginSenha) throws SQLException{
		Administrador administrador = new Administrador();
		AdministradorDao admDao = new AdministradorDao(new ConnectionProvider().getConnection());
		administrador = admDao.getPorLogin(loginSenha.getLogin());
		return administrador;
	}
	
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

//		Administrador admin = new Administrador();
//		Secretaria secretaria = new Secretaria();
//		Medico medico = new Medico();
//		LoginSenha loginSenha;
		
		validaUsuario();
	}
}
