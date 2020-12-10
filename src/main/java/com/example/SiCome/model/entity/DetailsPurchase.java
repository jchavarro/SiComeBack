/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "details_purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsPurchase {
    
    @Id
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_purchase", referencedColumnName = "id")
    private Purchase purchase;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;  
    
    private int quantity;
    

}
