package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import lombok.AllArgsConstructor;

/**
 * Violation Object representation.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Likith
 */
@AllArgsConstructor
public class Violation {

  private String fieldName;
  private String message;
}
