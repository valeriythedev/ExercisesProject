package by.liashuk.exercisesProject.exceptions.controller;


import by.liashuk.exercisesProject.exceptions.InvalidDataException;
import by.liashuk.exercisesProject.exceptions.NoSuchRecordException;
import by.liashuk.exercisesProject.exceptions.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("by.liashuk.runnerProject.controller")
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionInfo> handleInvalidDataException(InvalidDataException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST);
        log.warn("IN GlobalControllerAdvice handleInvalidDataException() exception: {}", exception.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchRecordException.class)
    public ResponseEntity<ExceptionInfo> handleNoSuchRecordException(NoSuchRecordException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST);
        log.warn("IN GlobalControllerAdvice handleNoSuchRecordException() exception: {}", exception.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ExceptionInfo> handleNotAuthorizedException(NotAuthorizedException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        log.warn("IN GlobalControllerAdvice handleNotAuthorizedException() exception: {}",exception.getMessage());
        info.setMessage("You should login to get access.");
        return new ResponseEntity<>(info, HttpStatus.UNAUTHORIZED);
    }
}
