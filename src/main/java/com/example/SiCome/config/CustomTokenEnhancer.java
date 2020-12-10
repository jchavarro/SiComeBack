/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.config;

import com.example.SiCome.model.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth){
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        Object principal = auth.getPrincipal();
        OAuth2Authentication oAuth;
        if (principal instanceof User) {
            User user = (User) principal;
            Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
            info.put("cedula", user.getCedula());
            customAccessToken.setAdditionalInformation(info);
            List<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach((role) -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                role.getPermissions().forEach(permission -> {
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                });
            });
            Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), authorities);
            oAuth = new OAuth2Authentication(auth.getOAuth2Request(), newAuth);
        } else {
            oAuth = auth;
        }
        return super.enhance(customAccessToken, oAuth);
    }
}
