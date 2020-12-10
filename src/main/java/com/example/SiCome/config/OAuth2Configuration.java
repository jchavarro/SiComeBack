/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter{
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    
    @Bean
    public CustomOAuth2RequestFactory requestFactory(){
        CustomOAuth2RequestFactory customOAuth2RequestFactory = new CustomOAuth2RequestFactory(clientDetailsService);
        customOAuth2RequestFactory.setCheckUserScopes(true);
        return customOAuth2RequestFactory;
    }
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new CustomTokenEnhancer();
        jwtAccessTokenConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("sicome.jks"),
                "si-come".toCharArray()).getKeyPair("si-come-jwt"));
        return jwtAccessTokenConverter;
    }
    
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception{
        client.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }
    
    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter(){
        return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oAuthorizationServerSecurityConfigurer){
        oAuthorizationServerSecurityConfigurer.tokenKeyAccess("permiteAll()").checkTokenAccess("isAuthenticated()");   
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer){
        authorizationServerEndpointsConfigurer.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        authorizationServerEndpointsConfigurer.requestFactory(requestFactory());
    }
}