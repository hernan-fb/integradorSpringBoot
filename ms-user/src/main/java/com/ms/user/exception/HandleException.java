package com.ms.user.exception;

import com.ms.user.config.ExceptionConfigs;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor // para poder inyectar dependencias tiene que haber constructor
public class HandleException {
    private final ExceptionConfigs exceptionConfigs;

    // @Value("${control.exception}")
    // private String businessException;

    @ExceptionHandler(MyHandledException.class)
    public ResponseEntity<Object> handleMyException(MyHandledException ex){
        return ResponseEntity.badRequest().body(String.format("""
                %s : %s""", exceptionConfigs.getTypeException(ExceptionConfigs.BUSINESS), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOthersException(Exception ex) {
        return ResponseEntity.badRequest().body(String.format("""
                System Exception: %s""", ex.getCause()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleSpecificException(MethodArgumentNotValidException ex) {
        List<String> errorList = new ArrayList<>();

        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errorList.add(error.getObjectName() + ": " + error.getField() + ":" + error.getDefaultMessage() );
        }

        return ResponseEntity.badRequest().body(errorList);
    }
}
