import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;
import br.com.totvs.clinica.dao.MedicoDao;
import br.com.totvs.clinica.dao.SecretariaDao;
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
			loginDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
			loginDao.inserir(adminLogin);
			adminDao = new AdministradorDao(new ConnectionProvider().getConnection());
			adminDao.inserir(admin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void iniciaSistema() throws SQLException{
		
		LoginSenhaDao loginSenhaDao;
		LoginSenha loginSenha;
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome do usuário: ");
		String login = sc.next();
		loginSenhaDao = new LoginSenhaDao(new ConnectionProvider().getConnection());
		loginSenha = loginSenhaDao.getPorLogin("'"+login+"'");
		
		if (loginSenha.getLogin().equals(login))
		{
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
						Medico medico = buscaMedico(loginSenha);
						System.out.println("Seja Bem-Vindo "+ medico.getNome());
						op = medico.operaMedico();
						if (op == 0)
							iniciaSistema();
						break;
					default:
						Secretaria secretaria = buscaSecretaria(loginSenha);
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

	
	public static Secretaria buscaSecretaria(LoginSenha loginSenha) throws SQLException{
		Secretaria secretaria = new Secretaria();
		SecretariaDao secDao = new SecretariaDao(new ConnectionProvider().getConnection());
		secretaria = secDao.getPorLogin(loginSenha.getLogin());
		return secretaria;
	}
	
	public static Medico buscaMedico(LoginSenha loginSenha) throws SQLException{
		Medico medico = new Medico();
		MedicoDao medicoDao = new MedicoDao(new ConnectionProvider().getConnection());
		medico = medicoDao.getPorLogin(loginSenha.getLogin());
		return medico;
	}
	
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		//iniciaAdmin();
		iniciaSistema();
		
	}
}
