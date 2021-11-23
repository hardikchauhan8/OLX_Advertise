package com.olx.service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasterDataDelegateImpl implements MasterDataDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "ALL-CATEGORIES-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetCategories")
    public List<Category> getCategories() {
        return restTemplate.getForObject("http://master-data-service/olx/advertise/category", List.class);
    }

    public List<Category> fallbackGetCategories(Throwable ex) {
        System.out.println("Error in getting all categories -> " + ex.getMessage());
        return new ArrayList<>();
    }

    @Override
    @CircuitBreaker(name = "CATEGORY-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetCategoryById")
    public Category getCategoryById(int categoryId) {
        return restTemplate.getForObject("http://master-data-service/olx/advertise/category/{id}", Category.class, categoryId);

    }

    public Category fallbackGetCategoryById(int categoryId, Throwable ex) {
        System.out.println("Error in getting all categories -> " + ex.getMessage());
        return null;
    }

    @Override
    @CircuitBreaker(name = "ALL-STATUS-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetStatus")
    public List<Status> getStatus() {
        return restTemplate.getForObject("http://master-data-service/olx/advertise/status", List.class);
    }

    public List<Status> fallbackGetStatus(Throwable ex) {
        System.out.println("Error in getting all status -> " + ex.getMessage());
        return new ArrayList<>();
    }

    @Override
    @CircuitBreaker(name = "STATUS-CIRCUIT_BREAKER", fallbackMethod = "fallbackGetStatusById")
    public Status getStatusById(int statusId) {
        return restTemplate.getForObject("http://master-data-service/olx/advertise/status/{id}", Status.class, statusId);
    }

    public Status fallbackGetStatusById(int categoryId, Throwable ex) {
        System.out.println("Error in getting status by id -> " + ex.getMessage());
        return null;
    }
}
