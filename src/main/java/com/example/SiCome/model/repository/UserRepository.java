/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.model.repository;

import com.example.SiCome.model.entity.User;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByApellidoContainsIgnoreCase(String apellido);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String emai);
}
