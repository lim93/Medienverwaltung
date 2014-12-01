package de.yellow.medienverwaltung.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest {
	protected static Connection getConnection(String dbUrl, String userId,
			String userPwd) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, userId, userPwd);
		} catch (ClassNotFoundException e) {
			throw new SQLException("JDBC driver not found!");
		}
	} // end getConnection

	public static void main(String[] args) {
		try {

			String dbUrl = "jdbc:mysql://localhost:3306/media";

			Connection conn = getConnection(dbUrl, "root", "");
			System.out.println("\nConnected to sample database!\n");

			conn.setAutoCommit(false);

			PreparedStatement stmt = conn
					.prepareStatement("select track_id, title from track");

			ResultSet rs = stmt.executeQuery();

			System.out.println("Titel-ID --- Titel");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
			}

			rs.close();
			conn.commit();
			stmt.clearParameters();
			conn.close();

		}

		catch (SQLException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
