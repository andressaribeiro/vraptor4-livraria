package br.com.casadocodigo.livraria.servico;

public class ServidorIndisponivelException extends Exception {
	
	public ServidorIndisponivelException(String url, Exception e) {
		super("Erro ao fazer requisição ao servidor na url: " + url, e);
	}

}
