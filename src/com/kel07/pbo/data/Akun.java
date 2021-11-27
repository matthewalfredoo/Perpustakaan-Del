package com.kel07.pbo.data;

public abstract class Akun {
	protected String email;
	protected String username;
	protected String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public abstract void login();
	public abstract void register();
	public abstract void editAkun();
}
