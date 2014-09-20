package br.com.casadocodigo.livraria.seguranca;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;

@Intercepts
public class AuditoriaInterceptor {

	@Inject	private Logger logger;
	@Inject private HttpServletRequest request;

	@BeforeCall
	public void loga() {
		logger.info("Usuário acessou {}", request.getRequestURI());
	}
}
