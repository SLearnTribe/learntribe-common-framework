package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import lombok.Getter;

/**
 * Run-time exception for handling invalid db id's or entity data.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Getter
public class InvalidDataException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String field;

  /**
   * Constructor for the message
   *
   * @param message the message
   */
  public InvalidDataException(String message) {
    super(message);
  }
}
