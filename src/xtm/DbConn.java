package xtm;

import java.sql.*;

public class DbConn {
	Connection con;
	Statement stmt;
	public DbConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xtm", "root", "1123");
			stmt = con.createStatement();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
