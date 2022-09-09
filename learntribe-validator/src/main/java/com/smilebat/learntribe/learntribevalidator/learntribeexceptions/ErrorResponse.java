package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * ErrorResponse representation.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Likith
 */
@Getter
public class ErrorResponse {

  private List<Violation> violations = new ArrayList<>();
}
