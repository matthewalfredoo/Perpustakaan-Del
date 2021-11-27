package com.kel07.pbo.data;

public class Dosen extends Akun {
	private String nidn;
	private String nama;
	private String noTelepon;

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
	
	public void searchDosen(String nidn) {
		// TODO buat query di class Database.java pada sebuah method untuk mencari data dosen ke Database
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editAkun() {
		// TODO Auto-generated method stub
		
	}
	
}
