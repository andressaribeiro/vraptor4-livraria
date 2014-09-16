package br.com.casadocodigo.livraria.controlador;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.casadocodigo.livraria.modelo.Arquivo;
import br.com.casadocodigo.livraria.modelo.Diretorio;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;

import com.google.common.io.ByteStreams;

@Controller
public class LivrosController {

	private Estante estante;
	private Result result;
	private Validator validator;
	private Diretorio imagens;

	@Inject
	public LivrosController(Estante estante, Diretorio imagens, Result result, Validator validator) {
		this.estante = estante;
		this.imagens = imagens;
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

	@Transactional
	@Post("/livros")
	public void salva(Livro livro, UploadedFile capa) throws IOException {

		if (livro.getTitulo() == null) {
			validator.add(new SimpleMessage("titulo", "Título é obrigatório"));
		}

		if (livro.getPreco() == null) {
			validator.add(new SimpleMessage("preco", "Preço é obrigatório!"));
		}

		validator.onErrorRedirectTo(this).formulario();

		if (capa != null) {
			URI imagemCapa = imagens.grava(new Arquivo(capa.getFileName(), ByteStreams.toByteArray(capa.getFile()), capa.getContentType(), Calendar
					.getInstance()));
			livro.setCapa(imagemCapa);
		}

		estante.guarda(livro);

		result.include("mensagem", "Livro salvo com sucesso!");
		result.redirectTo(LivrosController.class).lista();
	}

	@Get("/livros/{isbn}/capa")
	public Download capa(String isbn) {
		Livro livro = estante.buscaPorIsbn(isbn);
		Arquivo capa = imagens.recupera(livro.getCapa());
		if (capa == null) {
			result.notFound();
			return null;
		}
		return new ByteArrayDownload(capa.getConteudo(), capa.getContentType(), capa.getNome());
	}

	@Get("/livros")
	public List<Livro> lista() {
		return estante.todosOsLivros();
	}

	public void edita(String isbn) {

		Livro livroEncontrado = estante.buscaPorIsbn(isbn);
		
		if (livroEncontrado == null) {
			result.notFound();
		} else {
			result.include(livroEncontrado);
			result.of(LivrosController.class).formulario();
		}
	}

}
