/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Serializable {
    @Id
    private Integer id;
    
    @Column(name = "date_realization")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dateRealization; 
        
    private String state;
    
    @Column(name = "total_cost")
    private int totalCost;
    
    private String address; 
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_user", referencedColumnName = "cedula")
    private User user;
}    
