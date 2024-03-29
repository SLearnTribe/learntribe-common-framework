package com.smilebat.learntribe.learntribeclients.keycloak;

import com.smilebat.learntribe.keycloak.response.UserRepresentation;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * keycloak userprofile feign client
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@FeignClient(name = "KEYCLOAK", url = "${feign.client.url.keycloak}/admin/realms/master")
public interface KeycloakService {

  /**
   * Retrieves all users from IAM
   *
   * @return {@link String}
   */
  @GetMapping(path = "users")
  List<UserRepresentation> fetchAllUsers();

  /**
   * Returns the matching user based on email, empty otherwise.
   *
   * @param email the input {@link String}
   * @return the Stream of {@link UserRepresentation}
   */
  @GetMapping(path = "users")
  List<UserRepresentation> fetchUserByEmail(@RequestParam(value = "email") String email);

  /**
   * Returns the matching user based on user id, empty otherwise.
   *
   * @param userId the input {@link String}
   * @return the Stream of {@link UserRepresentation}
   */
  @GetMapping(path = "users/{id}")
  UserRepresentation fetchUserById(@PathVariable("id") String userId);

  /**
   * Logs out all user session.
   *
   * @param authorizationHeader the auth bearer token.
   * @param userId the IAM user id.
   * @return the {@link ResponseEntity}.
   */
  @PostMapping(path = "users/{id}/logout")
  ResponseEntity<String> logout(
      @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
      @PathVariable("id") String userId);
}
