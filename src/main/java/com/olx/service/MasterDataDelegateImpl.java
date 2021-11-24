package com.olx.service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MasterDataDelegateImpl implements MasterDataDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "ALL-CATEGORIES-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetCategories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(restTemplate.getForObject("http://api-gateway/olx/masterdata/category", List.class), HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> fallbackGetCategories(Throwable ex) {
        System.out.println("Error in getting all categories -> " + ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @CircuitBreaker(name = "CATEGORY-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetCategoryById")
    public ResponseEntity<Category> getCategoryById(int categoryId) {
        return new ResponseEntity<>(restTemplate.getForObject("http://api-gateway/olx/masterdata/category/{id}", Category.class, categoryId), HttpStatus.OK);

    }

    public ResponseEntity<Category> fallbackGetCategoryById(int categoryId, Throwable ex) {
        System.out.println("Error in getting all categories -> " + ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @CircuitBreaker(name = "ALL-STATUS-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetStatus")
    public ResponseEntity<List<Status>> getStatus() {
        return new ResponseEntity<>(restTemplate.getForObject("http://api-gateway/olx/masterdata/status", List.class), HttpStatus.OK);
    }

    public ResponseEntity<List<Status>> fallbackGetStatus(Throwable ex) {
        System.out.println("Error in getting all status -> " + ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @CircuitBreaker(name = "STATUS-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetStatusById")
    public ResponseEntity<Status> getStatusById(int statusId) {
        return new ResponseEntity<>(restTemplate.getForObject("http://api-gateway/olx/masterdata/status/{id}", Status.class, statusId), HttpStatus.OK);
    }

    public ResponseEntity<Status> fallbackGetStatusById(int categoryId, Throwable ex) {
        System.out.println("Error in getting status by id -> " + ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
