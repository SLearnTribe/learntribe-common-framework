package com.smilebat.learntribe.learntribevalidator.validatorannotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, Long> {
  @Override
  public boolean isValid(Long phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
    String phno = phoneNumber.toString();
    if (phno.length() == 10) return true;
    else return false;
  }
}
