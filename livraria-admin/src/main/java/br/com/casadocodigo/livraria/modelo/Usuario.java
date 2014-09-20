package br.com.casadocodigo.livraria.modelo;

public class Usuario {

	private String login;
	private String senha;
	private boolean admin;

	public Usuario(String login, String senha, boolean admin) {
		this.login = login;
		this.senha = senha;
		this.admin = admin;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAdmin() {
		return admin;
	}

}
