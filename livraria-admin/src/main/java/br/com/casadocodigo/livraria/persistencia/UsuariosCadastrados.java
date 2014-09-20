package br.com.casadocodigo.livraria.persistencia;

import br.com.casadocodigo.livraria.modelo.RegistroDeUsuarios;
import br.com.casadocodigo.livraria.modelo.Usuario;

public class UsuariosCadastrados implements RegistroDeUsuarios {

	@Override
	public Usuario comLoginESenha(String login, String senha) {
		
		if("admin".equals(login) && "admin".equals(senha)) {
			return new Usuario(login, senha, true);
		}
		
		return null;
	}

}
