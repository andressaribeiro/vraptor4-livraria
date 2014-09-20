package br.com.casadocodigo.livraria.controlador;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.casadocodigo.livraria.modelo.RegistroDeUsuarios;
import br.com.casadocodigo.livraria.modelo.Usuario;
import br.com.casadocodigo.livraria.seguranca.UsuarioLogado;

@Controller
public class LoginController {

	private RegistroDeUsuarios usuarios;
	private UsuarioLogado logado;
	private Result result;
	private Validator validator;

	@Inject
	public LoginController(RegistroDeUsuarios usuarios, UsuarioLogado logado, Result result, Validator validator) {
		this.usuarios = usuarios;
		this.logado = logado;
		this.result = result;
		this.validator = validator;
	}
	
	@Deprecated public LoginController() {}

	@Get("/login")
	public void formulario() {
	}

	@Post("/login")
	public void login(String login, String senha) {
		Usuario usuario = usuarios.comLoginESenha(login, senha);
		if (usuario == null) {
			validator.add(new I18nMessage("usuario", "login.ou.senha.invalidos"));
		}

		validator.onErrorRedirectTo(this).formulario();
		
		logado.loga(usuario);
		
		result.redirectTo(LivrosController.class).lista();
	}

	@Get("/logout")
	public void logout() {
		logado.desloga();
		result.redirectTo(this).formulario();
	}
}
