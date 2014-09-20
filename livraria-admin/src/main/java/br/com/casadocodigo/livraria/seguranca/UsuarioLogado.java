package br.com.casadocodigo.livraria.seguranca;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.com.casadocodigo.livraria.modelo.Usuario;

@SessionScoped
public class UsuarioLogado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2811985698531084206L;

	private Usuario usuario;

	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return this.usuario != null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void desloga() {
		this.usuario = null;
	}
}
