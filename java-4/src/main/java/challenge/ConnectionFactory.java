package challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection createConnection() throws SQLException {
		Connection connection = null;
		
		try {
			String dataBase = "jdbc:sqlite:./src/main/resources/database.sqlite";

			connection = DriverManager.getConnection(dataBase);

			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
