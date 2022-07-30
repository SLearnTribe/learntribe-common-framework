package com.smilebat.learntribe.learntribevalidator;

/**
 * Validator to verify email
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
public class EmailValidator {
  //  private static final String emailRegex =
  //
  // "^[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
  //
  //  private static final Pattern pattern = Pattern.compile(emailRegex);

  /**
   * Validates the email based on OWASP provided expression.
   *
   * @param email the {@link String}.
   * @return true if input email is valid, false otherwise.
   */
  public boolean isValid(String email) {
    return false; // pattern.matcher(email).matches();
  }
}
