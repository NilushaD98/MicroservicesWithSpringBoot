package com.microservice.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
//    @CircuitBreaker(name = "sample-api",fallbackMethod = "hardCodedResponse")
    @RateLimiter(name = "default")
    public String sampleAPI(){
        log.info("fuck");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return "sample";
    }
    public String hardCodedResponse(Exception ex){
        return "Fallback Method";
    }
}
