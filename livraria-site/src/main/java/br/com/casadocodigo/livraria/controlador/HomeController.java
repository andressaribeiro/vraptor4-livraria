package br.com.casadocodigo.livraria.controlador;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.casadocodigo.livraria.modelo.Acervo;

@Controller
public class HomeController {

	private Result result;
	private Acervo acervo;

	@Inject
	public HomeController(Acervo acervo, Result result) {
		this.result = result;
		this.acervo = acervo;
	}

	/**
	 * @deprecated Apenas para o CDI.
	 */
	HomeController() {
	}

	public void inicio() {
		this.result.include("livros", acervo.todosOsLivros());
	}

}
