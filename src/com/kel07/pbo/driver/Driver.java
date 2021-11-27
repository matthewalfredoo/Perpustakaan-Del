package com.kel07.pbo.driver;

import java.sql.Connection;
import java.sql.SQLException;

import com.kel07.pbo.database.*;

public class Driver {
	public static void main(String[] args) {
		Connection con = Database.connectDatabase("jdbc:mysql://127.0.0.1:3306/db_perpustakaan?useSSL=false", "root", "");
		
		try {
			if(con.isClosed()) {
				System.out.println("Koneksi ke database tertutup");
				System.exit(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
