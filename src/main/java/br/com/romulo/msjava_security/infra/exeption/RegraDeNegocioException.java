package br.com.romulo.msjava_security.infra.exeption;

public class RegraDeNegocioException extends RuntimeException {
	public RegraDeNegocioException(String message) {
        super(message);
    }
}
