package by.liashuk.exerciseproject.exceptions.controller;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice("by.liashuk.exerciseproject.controller")
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionInfo> handleNullPointerException(NullPointerException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), exception.getStackTrace()[0].getFileName(), exception.getStackTrace()[0].getLineNumber(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(info, info.getStatus());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionInfo> handleJwtException(JwtException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), exception.getStackTrace()[0].getFileName(), exception.getStackTrace()[0].getLineNumber(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(info, info.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionInfo> handleConstraintViolationException(ConstraintViolationException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), exception.getStackTrace()[0].getFileName(), exception.getStackTrace()[0].getLineNumber(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(info, info.getStatus());
    }
}
