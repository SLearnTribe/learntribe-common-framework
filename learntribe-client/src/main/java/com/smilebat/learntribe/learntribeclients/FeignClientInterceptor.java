package com.smilebat.learntribe.learntribeclients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Client interceptor for custom headers
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Component
@Slf4j
public class FeignClientInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String TOKEN_TYPE = "Bearer";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    AbstractOAuth2TokenAuthenticationToken authentication =
        (AbstractOAuth2TokenAuthenticationToken)
            SecurityContextHolder.getContext().getAuthentication();
    Target<?> target = requestTemplate.feignTarget();

    if (target != null && "LinkedIn".equals(target.name())) {
      log.info("Fetch linked in token here");
    }

    if (target != null && "KEYCLOAK_PROVIDER".equals(target.name())) {
      log.info("Accessing realm management");
      requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
    }

    if (authentication != null) {
      requestTemplate.header(
          AUTHORIZATION_HEADER,
          String.format("%s %s", TOKEN_TYPE, authentication.getToken().getTokenValue()));
      requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
    }
  }
}
