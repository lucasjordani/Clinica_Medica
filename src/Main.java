import java.io.UnsupportedEncodingException;
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
			loginDao = new LoginSenhaDao();
			loginDao.inserir(adminLogin);
			adminDao = new AdministradorDao();
			adminDao.inserir(admin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void iniciaSistema() throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome do usuário: ");
		String login = sc.next();
		LoginSenhaDao loginSenhaDao = new LoginSenhaDao();
		LoginSenha loginSenha = loginSenhaDao.getPorLogin(login);
		if (loginSenha.getLogin() != null && loginSenha.getLogin().equals(login)){
			System.out.println("Digite a senha: ");
			String senha = sc.next();
			if (senha.equals(loginSenha.getSenha())){
				int op;
				switch (loginSenha.getNivel()){
					case 1:
						Administrador adm = new Administrador();
						adm.buscaAministrador(loginSenha);
						System.out.println("Seja Bem-Vindo "+ adm.getNome());
						op = adm.operaAdministrador();
						if (op == 0)
							iniciaSistema();
						break;
					case 2:
						Medico medico = new Medico();
						medico.buscaMedico();
						System.out.println("Seja Bem-Vindo "+ medico.getNome());
						op = medico.operaMedico();
						if (op == 0)
							iniciaSistema();
						break;
					case 3:
						Secretaria secretaria = new Secretaria();
						secretaria.buscaSecretaria();
						System.out.println("Seja Bem-Vindo "+ secretaria.getNome());
						op = secretaria.operaSecretaria();
						if (op == 0)
							iniciaSistema();
						break;
				}
			}else{
				System.out.println("Senha inválida!");
				iniciaSistema();
			}
		}else{
			System.out.println("Usuário não cadastrado!");
			iniciaSistema();
		}
	}

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		//iniciaAdmin();
		iniciaSistema();
		
		Administrador adm = new Administrador();
		
	}
}
