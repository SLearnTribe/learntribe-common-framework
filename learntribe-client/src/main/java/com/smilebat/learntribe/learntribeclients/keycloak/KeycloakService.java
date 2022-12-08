package com.smilebat.learntribe.learntribeclients.keycloak;

import com.smilebat.learntribe.keycloak.response.UserRepresentation;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
   * Clears the user session and logs out.
   *
   * @param userId the IAM user id.
   */
  @PostMapping(path = "users/{id}/logout")
  void logout(@PathVariable("id") String userId);
}
