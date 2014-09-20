package br.com.casadocodigo.livraria.seguranca;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.com.casadocodigo.livraria.controlador.LoginController;

@Intercepts(after = AutenticacaoInterceptor.class)
public class AutorizacaoInterceptor {
	private UsuarioLogado usuario;
	private Result result;

	@Inject
	public AutorizacaoInterceptor(UsuarioLogado usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}
	
	@Deprecated AutorizacaoInterceptor() { }
	
	@Accepts
	public boolean isRestrito(ControllerMethod method) {
		return !method.getController().getType().equals(LoginController.class);
	}

	@AroundCall
	public void autoriza(SimpleInterceptorStack stack, ControllerMethod method) {
		if (podeAcessar(method)) {
			stack.next();
		} else {
			result.use(Results.http()).sendError(401, "Você não está autorizado!");
		}

	}

	private boolean podeAcessar(ControllerMethod method) {
		return method.containsAnnotation(Get.class) || this.usuario.getUsuario().isAdmin();
	}
	
}