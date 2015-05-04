package br.com.totvs.clinica.business;

import java.sql.SQLException;
import java.util.List;

import br.com.totvs.clinica.model.Endereco;
import br.com.totvs.clinica.model.Administrador;
import br.com.totvs.clinica.model.LoginSenha;
import br.com.totvs.clinica.model.Usuario;
import br.com.totvs.clinica.dao.AdministradorDao;
import br.com.totvs.clinica.dao.LoginSenhaDao;

public class AdministradorBo {
	
	private AdministradorDao administradorDao;
	private LoginSenhaDao loginDao;
	
	public AdministradorBo() throws SQLException {
		administradorDao = new AdministradorDao();
		loginDao = new LoginSenhaDao();
	}
	
	public void cadastrar(Administrador administrador) throws SQLException{
//		String login = loginDao.inserir(administrador.getUsuario());
		
//		administrador.getEndereco().setCodigoEndereco(codEndereco);
//		administrador.getUsuario().setCodigoUsuario(codigoUsuario);
		
		administradorDao.inserir(administrador);		
	}
	
	public void excluir(Administrador administrador) throws SQLException {
		administradorDao.excluirPorLogin(administrador.getLogin());
		
	}

	public List<Administrador> getTodos() throws SQLException {		
		List<Administrador> administradores = administradorDao.getTodos();
		
		for(Administrador admin : administradores){
//			admin.setLogin(LoginSenhaDao.getPorLogin((Usuario)admin.getLogin());
		}
		
		return administradores;
	}

}
