package br.com.casadocodigo.livraria.seguranca;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.casadocodigo.livraria.controlador.LoginController;

@Intercepts
public class AutenticacaoInterceptor {

	private UsuarioLogado usuario;
	private Result result;

	@Inject
	public AutenticacaoInterceptor(UsuarioLogado usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}

	@Deprecated
	AutenticacaoInterceptor() {
	}

	@Accepts
	public boolean isRestrito(ControllerMethod method) {
		return !method.getController().getType().equals(LoginController.class);
	}

	@AroundCall
	public void autentica(SimpleInterceptorStack stack) {

		if (this.usuario.isLogado()) {
			stack.next();
		} else {
			result.redirectTo(LoginController.class).formulario();
		}

	}
}
