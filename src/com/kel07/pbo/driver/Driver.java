package com.kel07.pbo.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.kel07.pbo.data.Buku;
import com.kel07.pbo.data.Dosen;
import com.kel07.pbo.database.*;

public class Driver {
	public static boolean sessionLogin = false; 
	public static Scanner sc = new Scanner(System.in);
	public static Dosen sessionDosen = null;
	
	public static void main(String[] args) throws SQLException {
		Connection con = Database.connectDatabase("jdbc:mysql://127.0.0.1:3306/db_perpustakaan?useSSL=false", "root", "");
		
		try {
			if(con.isClosed()) {
				System.err.println("Koneksi ke database tertutup");
				System.exit(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int pilihan;
		
		while (!sessionLogin) {
			Functions.menuAutentikasi();
			pilihan = Integer.parseInt(sc.nextLine());
			switch (pilihan) {
			case 1: 
				sessionDosen = Dosen.login(con);
				if(sessionDosen != null) {
					sessionLogin = true;
					System.out.println("Login berhasil!");
				}
				else{
					System.out.println("Username atau password Anda tidak dikenali");
				}
				break;
			case 2:
				Dosen.register(con);
				break;
			
			case 99:
				System.exit(0);
			default:
				System.out.println("Pilihan tidak tepat");
				break;
			}
		}
		
		while(sessionLogin) {
			Functions.menuSetelahLogin();
			int option = Integer.parseInt(sc.nextLine());
			switch(option){
				case 1:
					Buku.cariBuku(con);
					break;
				case 2:
					Buku.donasiBuku(con, sessionDosen);
					break;
				case 3:
					Buku.editBuku(con, sessionDosen);
					break;
				case 4:

					break;
				case 5:
					Dosen.selectDosen(con, sessionDosen);
					// TODO selectDosen belum diimplementasilkan
					break;	
				case 6:
					Dosen.editAkun(con, sessionDosen);
			}
		}
		
	}
}
