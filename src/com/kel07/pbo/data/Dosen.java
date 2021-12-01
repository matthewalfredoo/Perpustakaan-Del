package com.kel07.pbo.data;

import java.util.Scanner;

import com.kel07.pbo.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dosen extends Akun {
	private String nidn;
	private String nama;
	private String noTelepon;

	public Dosen () { }
	
	public Dosen(String nidn, String nama, String noTelepon, String email, String username, String password) {
		this.nidn = nidn;
		this.nama = nama;
		this.noTelepon = noTelepon;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public Dosen (Dosen d) {
		nidn = d.getNidn();
		nama = d.getNama();
		noTelepon = d.getNoTelepon();
		email = d.getEmail();
		username = d.getUsername();
		password = d.getPassword();
	}

	public String getNidn() {
		return nidn;
	}

	public void setNidn(String nidn) {
		this.nidn = nidn;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoTelepon() {
		return noTelepon;
	}

	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}

	public static Dosen login(Connection con) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Dosen objDosen = new Dosen();
		boolean valid = false;

		// Ask for input
		System.out.print("Username: ");
		objDosen.setUsername(sc.nextLine());
		System.out.print("Password: ");
		objDosen.setPassword(sc.nextLine());

		// Getting the result set
		ResultSet rs = Database.validateDosen(con, objDosen);
		int count = 0;
		Dosen objDosen2 = null;
		
		while(rs.next()) {
			objDosen.setNama(rs.getString("t_d_nama"));
			objDosen.setNidn(rs.getString("t_d_nidn"));
			objDosen.setEmail(rs.getString("t_d_email"));
			objDosen.setNoTelepon(rs.getString("t_d_noTelepon"));
			objDosen.setUsername(rs.getString("t_d_username"));
			count++;
		}

		if(count == 1) {
			valid = true;
		}
		
		if(valid){
			objDosen2 = new Dosen(objDosen);
		}
		
		return objDosen2;
	}

	public static void register(Connection con) throws SQLException{
		Scanner sc = new Scanner(System.in);
		Dosen objDosen = new Dosen();

		System.out.print("Masukkan NIDN : ");
		objDosen.setNidn(sc.nextLine());

		System.out.print("Masukkan Nama : ");
		objDosen.setNama(sc.nextLine());

		System.out.print("Masukkan Nomor Telepon : ");
		objDosen.setNoTelepon(sc.nextLine());

		System.out.print("Masukkan Email : ");
		objDosen.setEmail(sc.nextLine());

		System.out.print("Masukkan Username : ");
		objDosen.setUsername(sc.nextLine());
		
		System.out.print("Masukkan Password : ");
		objDosen.setPassword(sc.nextLine());

		int response = Database.insertDosen(con, objDosen);
		if(response > 0) System.out.println("Berhasil mendaftarkan akun");
		else System.out.println("Gagal mendaftarkan akun");
	} 

	public static void editAkun(Connection con, Dosen sessionDosen) throws SQLException {
		String nidn, nama, notelp, email, username, password;
		nidn = sessionDosen.getNidn();
		nama = sessionDosen.getNama();
		notelp = sessionDosen.getNoTelepon();
		email = sessionDosen.getNoTelepon();
		username = sessionDosen.getUsername();
		password = sessionDosen.getPassword();

		String newnama, newnotelp, newemail, newpassword;

		System.out.printf("Halo, %s anda akan mengubah data akun anda\n", nama);
		System.out.println("Berikut adalah data lama anda : \n");
		System.out.printf("NIDN : %s\n", nidn);
		System.out.printf("Nama : %s\n", nama);
		System.out.printf("Nomor Telepon : %s\n", notelp);
		System.out.printf("Email : %s\n", email);
		System.out.printf("Username : %s\n", username);
		System.out.printf("password : %s\n", password);

		System.out.println("Masukkan data baru anda : ");
		System.out.println("Nama Baru: ");
		newnama = new Scanner(System.in).nextLine();
		System.out.println("Nomor Telepon Baru: ");
		newnotelp = new Scanner(System.in).nextLine();
		System.out.println("Email Baru: ");
		newemail = new Scanner(System.in).nextLine();
		System.out.println("Password Baru: ");
		newpassword = new Scanner(System.in).nextLine();
		
		String _sql = "UPDATE t_dosen SET t_d_nama = ?, t_d_noTelepon = ?, t_d_email = ?, t_d_password = ? WHERE t_d_nidn =?";
		PreparedStatement pStmt = con.prepareStatement(_sql);

		pStmt.setString(1, newnama);
		pStmt.setString(2, newnotelp);
		pStmt.setString(3, newemail);
		pStmt.setString(4, newpassword);
		pStmt.setString(5, nidn);

		int response = pStmt.executeUpdate();
		if(response > 0) System.out.printf("Akun dengan username %s telah berhasil diubah\n", username);
		else System.out.printf("Akun dengan username %s gagal diubah", username);
	}
	public static void cariDosen(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String NIDN = sc.nextLine();
        Database.selectDosen(con, NIDN);
	}

	public static void selectDosen(Connection con, Dosen sessionDosen) {
	}
}
