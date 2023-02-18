package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Run-time exception for handling bad request on input data.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Setter
@Getter
public class BeanValidationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String field;

  /**
   * Constructor
   *
   * @param message the message
   */
  public BeanValidationException(String message) {
    super(message);
  }

  /**
   * Constructor
   *
   * @param field the field
   * @param message the message
   */
  public BeanValidationException(String field, String message) {
    super(message);
    this.field = field;
  }
}
