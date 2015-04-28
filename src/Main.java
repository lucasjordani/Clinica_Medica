import java.sql.Connection;
import java.sql.SQLException;



public class Main {

	public static void main(String[] args) {
		Connection connection;

		try {



			connection = ConnectionProvider.getConnection();

			Dao dao = new Dao(connection);

			String text = "Hello again JDBC";

			dao.save(text);



		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
