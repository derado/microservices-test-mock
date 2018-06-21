package com.example.microservices.rate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "rate-service", url = "${rate.url}")
public interface RateClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rate")
    Rate findRate();

}
