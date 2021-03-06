package com.thesquad.microservice.listservice;

import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;


/**
 * Entry class for the list service.
 * This service serves a function to store and create personalised lists of movies
 * This service also has authentication enabled as well.
 *
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ListServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListServiceApplication.class, args);
    }

    @Configuration
    static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .cors()
                    .and().oauth2Client()
                    .and().oauth2Login();
        }
    }

    @Bean
    public RequestInterceptor getServiceFeignClientInterceptor(OAuth2AuthorizedClientService clientService) {
        return new ServiceFeignClientInterceptor(clientService);
    }
}
