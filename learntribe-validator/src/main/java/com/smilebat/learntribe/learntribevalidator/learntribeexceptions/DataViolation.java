package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception Response class
 *
 * <p>Copyright &copy; 2023 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Setter
@Getter
public class DataViolation {
  private int status;
  private String field;
  private String message;

  /**
   * Constructor
   *
   * @param status the HTTP status
   * @param field the field
   * @param message the message
   */
  public DataViolation(int status, String field, String message) {
    this.status = status;
    this.field = field;
    this.message = message;
  }

  /**
   * Constructor
   *
   * @param status the HTTP status
   * @param message the message
   */
  public DataViolation(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
