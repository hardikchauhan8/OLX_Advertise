package com.olx.service;

import org.springframework.http.ResponseEntity;

public interface LoginDelegate {
    ResponseEntity<Boolean> validateToken(String authToken);

    ResponseEntity<String> getUsername(String authToken);
}
