package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ErrorResponse onConstraintValidationException(ConstraintViolationException e) {
    ErrorResponse error = new ErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error
          .getViolations()
          .add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
      System.out.println(violation.getMessage());
    }
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ErrorResponse error = new ErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error
          .getViolations()
          .add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
      System.out.println(fieldError.getDefaultMessage());
    }
    return error;
  }
}
