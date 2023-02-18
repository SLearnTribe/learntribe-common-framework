package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * ErrorResponse representation.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Likith
 */
@Getter
@Setter
public class ErrorResponse {
  private int status;
  private List<Violation> violations = new ArrayList<>();
}
