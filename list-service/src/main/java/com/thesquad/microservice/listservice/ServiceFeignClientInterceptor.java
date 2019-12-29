package com.thesquad.microservice.listservice;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class ServiceFeignClientInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    private final OAuth2AuthorizedClientService clientService;

    public ServiceFeignClientInterceptor(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtAuthenticationToken oauthToken =(JwtAuthenticationToken) authentication;
        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,  oauthToken.getToken().getTokenValue()));
    }
}