import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {

	private Connection connection;

	public Dao(Connection connection) {

		this.connection = connection;
	}

	public void save(String text) throws SQLException {
		
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement statement = null;
	
		try {
			
		String sql = "INSERT INTO test_table (text) VALUES (?)";
		statement  = con.prepareStatement(sql);
		
		
		
		
		statement.setString(1, text);
		statement.execute();
		
		} catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
		
			statement.close();
			con.close();
		}
	}

}