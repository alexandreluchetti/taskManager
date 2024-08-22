package br.com.lucchetta.task_manager.configuration;

import br.com.lucchetta.task_manager.exception.NoneResultException;
import br.com.lucchetta.task_manager.exception.OperationException;
import br.com.lucchetta.task_manager.model.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(NoneResultException.class)
    public ResponseEntity<ExceptionEntity> handleNoneResultException(NoneResultException ex) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionEntity);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ExceptionEntity> handleOperationException(OperationException ex) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionEntity);
    }
}
