package ru.itis.semesterworkspring.controllers.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.semesterworkspring.dto.ValidationErrorDto;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        logger.error("Server error: " + e);
        return "code_pages/error";
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationException(MethodArgumentNotValidException e) {

        logger.error("ValidException has occurred");

        List<ValidationErrorDto> errors = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach((error) -> errors.add(ValidationErrorDto.builder()
                .message(error.getDefaultMessage())
                .build()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationExceptions(AuthenticationException authenticationException) {

        logger.error("Exception has occurred: " + authenticationException.getMessage());

        return "code_pages/error";
    }
}
