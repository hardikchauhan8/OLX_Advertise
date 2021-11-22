package com.olx;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@SpringBootApplication
public class OlxAdvertisesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlxAdvertisesApplication.class, args);
    }

    @Bean
    public Docket getCustomizedDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.olx"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "OLX Application Api Document",
                "Api documentation for OLX App - Advertises service",
                "1.0",
                "",
                new Contact("Hardik", "", "hardik.chauhan@zensar.com"),
                "GPL",
                "www.gpl.com",
                new ArrayList<>()
        );
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
