package br.com.productspringwsactivemqjms.exception;

public class QueueException extends RuntimeException {
    public QueueException(String message) {
        super(message);
    }
}
