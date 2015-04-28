package br.com.totvs.clinica.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	public List<T> getTodos() throws SQLException;

	public T getPorId(int id) throws SQLException;

	public void inserir(T t) throws SQLException;

}
