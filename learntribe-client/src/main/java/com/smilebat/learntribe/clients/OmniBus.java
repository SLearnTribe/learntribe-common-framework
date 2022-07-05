package com.smilebat.learntribe.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "omnibus", url = "${clients.omnibus.url}")
public interface OmniBus {
  @GetMapping(path = "api/v1/fraud-check/{customerId}")
  OmniBusResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
