package br.com.casadocodigo.livraria.servico;

import br.com.casadocodigo.livraria.servico.ServidorIndisponivelException;

public interface ClienteHTTP {

	String get(String url) throws ServidorIndisponivelException;

}
