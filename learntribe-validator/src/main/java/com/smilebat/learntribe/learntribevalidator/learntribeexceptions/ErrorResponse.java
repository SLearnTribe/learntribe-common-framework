package com.smilebat.learntribe.learntribevalidator.learntribeexceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

  private List<Violation> violations = new ArrayList<>();

  public List<Violation> getViolations() {
    return violations;
  }
}
