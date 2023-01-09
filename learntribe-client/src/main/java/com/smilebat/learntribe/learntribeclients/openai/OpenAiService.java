package com.smilebat.learntribe.learntribeclients.openai;

import com.smilebat.learntribe.keycloak.response.UserRepresentation;
import com.smilebat.learntribe.openai.OpenAiRequest;
import com.smilebat.learntribe.openai.response.OpenAiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * keycloak userprofile feign client
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@FeignClient(name = "OPENAI", url = "https://api.openai.com/v1")
public interface OpenAiService {
  /**
   * Returns the matching user based on user id, empty otherwise.
   *
   * @param request the input {@link OpenAiRequest}
   * @return the Stream of {@link UserRepresentation}
   */
  @GetMapping(path = "completions")
  OpenAiResponse getCompletions(@RequestBody OpenAiRequest request);
}
