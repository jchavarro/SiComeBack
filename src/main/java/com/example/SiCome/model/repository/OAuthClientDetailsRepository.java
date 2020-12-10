/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.model.repository;

import com.example.SiCome.model.entity.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String>{
    
}
