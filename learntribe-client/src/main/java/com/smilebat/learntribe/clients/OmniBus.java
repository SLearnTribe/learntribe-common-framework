package com.smilebat.learntribe.clients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "omnibus",
        url = "${clients.omnibus.url}"
)
public interface OmniBus {
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    OmniBusResponse isFraudster(
            @PathVariable("customerId") Integer customerId);
}
