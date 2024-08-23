package br.com.lucchetta.task_manager.configuration.exception;

public class OperationException extends RuntimeException {
    public OperationException(String message) {
        super(message);
    }
}
