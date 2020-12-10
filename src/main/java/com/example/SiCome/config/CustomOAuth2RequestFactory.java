/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.config;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class CustomOAuth2RequestFactory extends DefaultOAuth2RequestFactory {
    @Autowired 
    private TokenStore tokenStore;
    
    @Autowired 
    private UserDetailsService userDetailsService;

    public CustomOAuth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }
    
    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParam, ClientDetails authenticateCLient){
        if (requestParam.get("grant_type").equals("refresh_token")) {
            OAuth2Authentication auth = tokenStore.readAuthenticationForRefreshToken(tokenStore.readRefreshToken(requestParam.get("refresh_token")));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(auth.getName(), null, userDetailsService.loadUserByUsername(auth.getName()).getAuthorities()));
        }
        return super.createTokenRequest(requestParam, authenticateCLient);
    }
}

