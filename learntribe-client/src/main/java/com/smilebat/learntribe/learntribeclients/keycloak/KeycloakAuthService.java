package com.smilebat.learntribe.learntribeclients.keycloak;

import com.smilebat.learntribe.keycloak.response.AuthResponse;
import com.smilebat.learntribe.learntribeclients.CoreFeignConfiguration;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Keycloak User Session feign client.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@FeignClient(
    name = "AUTHKEYCLOAK",
    url = "${feign.client.url.keycloak}/realms/master",
    configuration = CoreFeignConfiguration.class)
public interface KeycloakAuthService {
  /**
   * Clears the user session and logs out.
   *
   * @param formParams the client cedentials.
   */
  @PostMapping(
      path = "/protocol/openid-connect/logout",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  void logout(Map<String, ?> formParams);

  /**
   * Creates a fresh user token.
   *
   * @param formParams the form url encoded values.
   * @return the {@link AuthResponse}.
   */
  @PostMapping(
      path = "/protocol/openid-connect/token",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @Cacheable(value = "authresponse")
  AuthResponse createToken(Map<String, ?> formParams);
}
