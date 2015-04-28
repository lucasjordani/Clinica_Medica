import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static final String URL = "jdbc:postgresql://localhost:5432/clinica";
	private static final String USER = "padrao";
	private static final String PASSWORD = "oi";

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null) { 

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectado com sucesso");

		}

		return connection;

	}

}