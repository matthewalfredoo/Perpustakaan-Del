package com.kel07.pbo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public static Connection connectDatabase(String connect, String username, String password) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(connect, username, password);
		} catch (SQLException e) {
			System.err.println("Gagal terhubung ke database");
			System.exit(1);
		}
		return con;
	}
	
	
	
}
