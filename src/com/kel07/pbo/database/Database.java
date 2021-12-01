package com.kel07.pbo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kel07.pbo.data.Buku;
import com.kel07.pbo.data.Dosen;

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

	public static ResultSet validateDosen(Connection con, Dosen objDosen) throws SQLException{
		String _sql = "SELECT * FROM t_dosen WHERE t_d_username=? AND t_d_password=?";
		PreparedStatement pStmt = con.prepareStatement(_sql);
		pStmt.setString(1, objDosen.getUsername());
		pStmt.setString(2, objDosen.getPassword());

		ResultSet rs = pStmt.executeQuery();
		return rs;
	}

	public static int insertDosen(Connection con, Dosen objDosen) throws SQLException{
		String _sql = "INSERT INTO t_dosen(t_d_nidn, t_d_nama, t_d_noTelepon, t_d_email, t_d_username, t_d_password) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = con.prepareStatement(_sql);

		pStmt.setString(1, objDosen.getNidn());
		pStmt.setString(2, objDosen.getNama());
		pStmt.setString(3, objDosen.getNoTelepon());
		pStmt.setString(4, objDosen.getEmail());
		pStmt.setString(5, objDosen.getUsername());
		pStmt.setString(6, objDosen.getPassword());

		int response = pStmt.executeUpdate();

		return response;
	}
	
	public static int insertBuku(Connection con, Buku b) throws SQLException{
		String _sql = "INSERT INTO t_buku VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = con.prepareStatement(_sql);
		pStmt.setString(1, b.getIsbn());
		pStmt.setString(2, b.getJudul());
		pStmt.setString(3, b.getJenis());
		pStmt.setString(4, b.getPenulis());
		pStmt.setInt(5, b.getStok());
		pStmt.setString(6, b.getNidnDonatur());
		pStmt.setInt(7, b.getKodeRak());

		int response = pStmt.executeUpdate();
		return response;
	}
	
	public static void selectBuku(Connection con, String ISBN)throws SQLException{
		String _sql = "SELECT * FROM t_buku WHERE t_b_ISBN = ?";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, ISBN);

        ResultSet rs = ps.executeQuery();
        try {
        while (rs.next()) {
            String getIsbn = rs.getString("t_b_ISBN");
            System.out.println("ISBN : " + getIsbn);
            String getJudul = rs.getString("t_b_judul");
            System.out.println("Judul : " + getJudul);
            String getJenis = rs.getString("t_b_jenis");
            System.out.println("Jenis : " + getJenis);
            String getPenulis = rs.getString("t_b_penulis");
            System.out.println("Penulis : " + getPenulis);
            String getStok = rs.getString("t_b_stok");
            System.out.println("Stok : " + getStok);
            String getNidnDonatur = rs.getString("t_b_nidnDonatur");
            System.out.println("NIDN Donatur : " + getNidnDonatur);
            String getKodeRak = rs.getString("t_b_kodeRak");
            System.out.println("Kode Rak : " + getKodeRak);
        }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            ps.close();
        }
	}
	public static void selectDosen(Connection con, String NIDN)throws SQLException{
		String _sql = "SELECT * FROM t_dosen WHERE t_d_nidn = ?";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, NIDN);

        ResultSet rs = ps.executeQuery();
        try {
        while (rs.next()) {
            String getNidn = rs.getString("t_d_nidn");
            System.out.println("NIDN : " + getNidn);
            String getNama = rs.getString("t_d_nama");
            System.out.println("Nama : " + getNama);
            String getNotelp = rs.getString("t_d_noTelepon");
            System.out.println("Nomor Telepon : " + getNotelp);
            String getEmail = rs.getString("t_d_email");
            System.out.println("Email : " + getEmail);
        }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            ps.close();
        }
	}
}
