package xtm;

import java.sql.*;

public class CreateDb {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql1, sql2, sql3, sql4, sql5;
	
	public CreateDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306", "root", "1123");
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS xtm");
			stmt.executeUpdate("USE xtm");
			sql1 = "CREATE TABLE IF NOT EXISTS login("
					+ "formno VARCHAR(255) NOT NULL, "
					+ "cardno VARCHAR(255) NOT NULL, "
					+ "pin VARCHAR(255) NOT NULL, "
					+ "PRIMARY KEY ( cardno ))";
			stmt.executeUpdate(sql1);
			sql2 = "CREATE TABLE IF NOT EXISTS bank("
					+ "cardno VARCHAR(255) NOT NULL, "
					+ "date VARCHAR(255), "
					+ "mode VARCHAR(255), "
					+ "amount VARCHAR(255))";
			stmt.executeUpdate(sql2);
			sql3 = "CREATE TABLE IF NOT EXISTS signup("
					+ "formno VARCHAR(255) NOT NULL, "
					+ "name VARCHAR(255), "
					+ "fname VARCHAR(255), "
					+ "dob VARCHAR(255), "
					+ "gender VARCHAR(255), "
					+ "email VARCHAR(255), "
					+ "martial VARCHAR(255), "
					+ "address VARCHAR(255), "
					+ "city VARCHAR(255), "
					+ "pincode VARCHAR(255), "
					+ "state VARCHAR(255), "
					+ "PRIMARY KEY ( formno ))";
			stmt.executeUpdate(sql3);
			sql4 = "CREATE TABLE IF NOT EXISTS signup2("
					+ "formno VARCHAR(255) NOT NULL, "
					+ "religion VARCHAR(255), "
					+ "category VARCHAR(255), "
					+ "income VARCHAR(255), "
					+ "education VARCHAR(255), "
					+ "occupation VARCHAR(255), "
					+ "pan VARCHAR(255), "
					+ "aadhar VARCHAR(255), "
					+ "scitizen VARCHAR(255), "
					+ "eaccount VARCHAR(255), "
					+ "PRIMARY KEY ( formno ))";
			stmt.executeUpdate(sql4);
			sql5 = "CREATE TABLE IF NOT EXISTS signup3("
					+ "formno VARCHAR(255) NOT NULL, "
					+ "atype VARCHAR(255), "
					+ "cardno VARCHAR(255) NOT NULL, "
					+ "pin VARCHAR(255) NOT NULL, "
					+ "facility VARCHAR(255), "
					+ "PRIMARY KEY ( formno ))";
			stmt.executeUpdate(sql5);
			// stmt.executeUpdate("INSERT INTO login VALUES('1234', '1234123412341234', '1234')");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
