package br.com.casadocodigo.livraria.servico;

public class ServidorIndisponivelException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2238079508176893358L;

	public ServidorIndisponivelException(String url, Exception e) {
		super("Erro ao fazer requisição ao servidor na url: " + url, e);
	}

}
