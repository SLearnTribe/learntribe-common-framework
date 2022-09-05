package com.smilebat.learntribe.learntribevalidator.validatorannotations;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {
  String message() default "{Invalid Phone Number}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
