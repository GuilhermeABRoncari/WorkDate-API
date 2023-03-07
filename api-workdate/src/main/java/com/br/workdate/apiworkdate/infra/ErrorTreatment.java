package com.br.workdate.apiworkdate.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(ErrorValidation::new).toList());
    }
    private record ErrorValidation(String field, String message){
        public ErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    @ExceptionHandler(AgendamentoException.class)
    private ResponseEntity agendamentoException(AgendamentoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

