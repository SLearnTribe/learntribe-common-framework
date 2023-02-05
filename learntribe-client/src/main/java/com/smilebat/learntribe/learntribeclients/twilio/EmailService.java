package com.smilebat.learntribe.learntribeclients.twilio;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Email service for twilio
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Slf4j
@Service
public class EmailService {

  private static final String API_KEY =
      "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";

  /** Twilio Email Concept and Builder */
  @Builder
  @Getter
  public static class TwEmail {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
  }

  /**
   * Function to send an email.
   *
   * @param twEmail the {@link TwEmail}.
   */
  public void sendMail(TwEmail twEmail) {
    Email from = new Email(twEmail.getFromEmail());
    String subject = twEmail.getSubject();
    Email to = new Email(twEmail.getToEmail());
    Content content = new Content("text/plain", twEmail.getBody());
    Personalization personalization = new Personalization();
    personalization.addCc(from);
    personalization.addTo(to);
    Mail mail = new Mail(new Email("no-reply@smilebat.xyz"), subject, to, content);
    mail.addPersonalization(personalization);

    SendGrid sg = new SendGrid(API_KEY);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      if (response.getStatusCode() == 400) {
        log.info("Something went wrong when sending email : {}", response.getBody());
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
