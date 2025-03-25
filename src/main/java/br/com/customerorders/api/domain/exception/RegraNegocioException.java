package br.com.customerorders.api.domain.exception;

public class RegraNegocioException extends RuntimeException {
	
	public RegraNegocioException(String message) {
        super(message);
    }
}
