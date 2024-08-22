package br.com.lucchetta.task_manager.exception;

public class OperationException extends RuntimeException {
    public OperationException(String message) {
        super(message);
    }
}
