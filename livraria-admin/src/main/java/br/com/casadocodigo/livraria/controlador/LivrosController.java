package br.com.casadocodigo.livraria.controlador;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;
import br.com.casadocodigo.livraria.persistencia.UmaEstanteQualquer;

@Controller
public class LivrosController {

	private Estante estante;
	private Result result;
	private Validator validator;

	@Inject
	public LivrosController(Estante estante, Result result, Validator validator) {
		this.estante = estante;
		this.result = result;
		this.validator = validator;
	}

	/**
	 * @deprecated Apenas para o CDI.
	 */
	LivrosController() {
		// TODO Auto-generated constructor stub
	}

	public void formulario() {

	}

	public void salva(Livro livro) {
		Estante estante = new UmaEstanteQualquer();

		if (livro.getTitulo() == null) {
			validator.add(new SimpleMessage("titulo", "Título é obrigatório!"));
		}

		if (livro.getPreco() == null) {
			validator.add(new SimpleMessage("preco", "Preço é obrigatório!"));
		}
		
		validator.onErrorRedirectTo(this).formulario();

		estante.guarda(livro);

		result.include("mensagem", "Livro salvo com sucesso!");
		result.redirectTo(this).lista();
	}

	public List<Livro> lista() {
		Estante estante = new UmaEstanteQualquer();
		return estante.todosOsLivros();
	}

	public void edita(String isbn) {
		Livro livroEncontrado = estante.buscaPorIsbn(isbn);

		if (livroEncontrado == null) {
			result.notFound();
		} else {
			result.include(livroEncontrado);
			result.of(this).formulario();
		}

	}

}
