/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service.impl;

import com.example.SiCome.model.entity.User;
import com.example.SiCome.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = null;
        if (userName.contains("@")) {
            user = repository.findByEmail(userName).orElse(null);
        } else {
            user = repository.findByUsername(userName).orElse(null);
        }
        if (user == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }
    
        
}
