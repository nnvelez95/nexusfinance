package com.nexusfinance.domain.exception;

/**
 * Excepción base para errores de lógica de negocio.
 * Ejemplos: saldo insuficiente, usuario no verificado.
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
