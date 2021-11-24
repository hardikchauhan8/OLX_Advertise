package com.olx.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoginDelegateImpl implements LoginDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "VALIDATE-TOKEN-CIRCUIT_BREAKER", fallbackMethod = "fallbackValidateToken")
    public ResponseEntity<Boolean> validateToken(String authToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange("http://api-gateway/olx/user/validate/token", HttpMethod.GET, entity, Boolean.class);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Boolean> fallbackValidateToken(String authToken, Throwable ex){
        System.out.println("Error in validating token -> " + ex.getMessage());
        return new ResponseEntity<>(false, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @CircuitBreaker(name = "USERNAME-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetUsername")
    public ResponseEntity<String> getUsername(String authToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange("http://api-gateway/olx/user/name", HttpMethod.GET, entity, String.class);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> fallbackGetUsername(String authToken, Throwable ex){
        System.out.println("Error in validating token -> " + ex.getMessage());
        return new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
