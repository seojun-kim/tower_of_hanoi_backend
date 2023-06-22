package kr.hs.dgsw.towerofhanoi.global.error.handler;

import kr.hs.dgsw.towerofhanoi.global.error.DuplicateNameException;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<?> duplicateNameExceptionHandler(DuplicateNameException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
