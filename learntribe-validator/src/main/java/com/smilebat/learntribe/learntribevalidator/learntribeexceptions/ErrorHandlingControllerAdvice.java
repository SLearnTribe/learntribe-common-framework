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

/**
 * Exception Handler class
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Likith
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

  /**
   * Exception handler
   *
   * @param e the {@link ConstraintViolationException}
   * @return the {@link ErrorResponse}
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse onConstraintValidationException(ConstraintViolationException e) {
    ErrorResponse error = new ErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error
          .getViolations()
          .add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    return error;
  }

  /**
   * Exception handler
   *
   * @param e the {@link MethodArgumentNotValidException}
   * @return the {@link ErrorResponse}
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ErrorResponse error = new ErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error
          .getViolations()
          .add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    return error;
  }

  /**
   * Exception handler
   *
   * @param ex the {@link BeanValidationException}
   * @return the {@link DataViolation}
   */
  @ExceptionHandler(BeanValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public DataViolation onBeanValidationException(BeanValidationException ex) {
    return new DataViolation(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * Exception handler
   *
   * @param ex the {@link InvalidDataException}
   * @return the {@link DataViolation}
   */
  @ExceptionHandler(InvalidDataException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public DataViolation onBeanValidationException(InvalidDataException ex) {
    return new DataViolation(
        HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getField(), ex.getMessage());
  }
}
