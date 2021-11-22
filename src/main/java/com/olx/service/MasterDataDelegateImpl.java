package com.olx.service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MasterDataDelegateImpl implements MasterDataDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public List<Category> getCategories() {
        return restTemplate.getForObject("http://localhost:9001/olx/advertise/category", List.class);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return restTemplate.getForObject("http://localhost:9001/olx/advertise/category/{id}", Category.class, categoryId);

    }

    @Override
    public List<Status> getStatus() {
        return restTemplate.getForObject("http://localhost:9001/olx/advertise/status", List.class);
    }

    @Override
    public Status getStatusById(int statusId) {
        return restTemplate.getForObject("http://localhost:9001/olx/advertise/status/{id}", Status.class, statusId);
    }
}
