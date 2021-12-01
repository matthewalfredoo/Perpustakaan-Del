package com.kel07.pbo.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.kel07.pbo.database.Database;

public class Buku {
	private String isbn;
	private String judul;
	private String jenis;
	private String penulis;
	private int stok;
	private String nidnDonatur;
	private int kodeRak;
	
	public Buku() {  }
	
	public Buku(String isbn, String judul, String jenis, String penulis, int stok, String nidnDonatur, int kodeRak) {
		super();
		this.isbn = isbn;
		this.judul = judul;
		this.jenis = jenis;
		this.penulis = penulis;
		this.stok = stok;
		this.nidnDonatur = nidnDonatur;
		this.kodeRak = kodeRak;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getPenulis() {
		return penulis;
	}

	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public String getNidnDonatur() {
		return nidnDonatur;
	}

	public void setNidnDonatur(String nidnDonatur) {
		this.nidnDonatur = nidnDonatur;
	}

	public int getKodeRak() {
		return kodeRak;
	}

	public void setKodeRak(int kodeRak) {
		this.kodeRak = kodeRak;
	}

	public static void donasiBuku(Connection con, Dosen d) {
        Scanner sc = new Scanner(System.in);
		Buku b = new Buku();
        
        System.out.print("Kode ISBN: ");
        b.setIsbn(sc.nextLine());

        System.out.print("Judul: ");
        b.setJudul(sc.nextLine());

        System.out.print("Jenis: ");
        b.setJenis(sc.nextLine());
        
        System.out.println("Penulis: ");
        b.setPenulis(sc.nextLine());

        System.out.println("Jumlah: ");
        b.setStok(Integer.parseInt(sc.nextLine()));

        b.setNidnDonatur(d.getNidn());
        
        System.out.println("Dimasukkan ke rak (1-5): ");
        b.setKodeRak(Integer.parseInt(sc.nextLine()));

        try {
            if(Database.insertBuku(con, b) == 1) {
                System.out.println("Berhasil menambahkan buku ke Database");
            }
            else{
                System.out.println("Gagal menambahkan Buku ke Database");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan Buku ke Database");
        }
	}
	
	public static void cariBuku(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String ISBN = sc.nextLine();
        Database.selectBuku(con, ISBN);
	}


	public static void editBuku(Connection con, Dosen d)  throws SQLException{
        // TODO buku yang tampil masih hanya 1
        
		Scanner sc = new Scanner(System.in);
		Buku listBuku = new Buku();

		String _sql = "SELECT * FROM t_buku";
		PreparedStatement ps = con.prepareStatement(_sql);
		ResultSet rs = ps.executeQuery();
		int i = 0;
		//ISBN, Judul, Jenis, Penulis, Jumlah, Donatur, Rak
		while(rs.next()){
			listBuku.setIsbn(rs.getString("t_b_ISBN"));
			listBuku.setJudul(rs.getString("t_b_judul"));
			listBuku.setJenis(rs.getString("t_b_jenis"));
			listBuku.setPenulis(rs.getString("t_b_penulis"));
			listBuku.setStok(rs.getInt("t_b_stok"));
			listBuku.setNidnDonatur(rs.getString("t_b_nidnDonatur"));
			listBuku.setKodeRak(rs.getInt("t_b_kodeRak"));
		}

		System.out.printf("\n%d.\n", i + 1);
		System.out.print("ISBN : ");
		System.out.print(listBuku.getIsbn() + "\n");
		System.out.print("Judul Buku: ");
		System.out.print(listBuku.getJudul() + "\n");
		System.out.print("Jenis Buku : ");
		System.out.print(listBuku.getJenis() + "\n");
		System.out.print("Penulis Buku : ");
		System.out.print(listBuku.getPenulis() + "\n");
		System.out.print("Stok Buku : ");
		System.out.print(listBuku.getStok() + "\n");
		System.out.print("NIDN Dosen Donatur : ");
		System.out.print(listBuku.getNidnDonatur() + "\n");
		System.out.print("Nomor Rak : ");
		System.out.print(listBuku.getKodeRak() + "\n");
		i++;

		System.out.println("Masukkan judul buku yang ingin diedit : ");

		String editBuku = new Scanner(System.in).nextLine();

	}
}
