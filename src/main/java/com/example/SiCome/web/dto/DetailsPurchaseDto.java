/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsPurchaseDto {
    
    private Integer id;
    
    private Integer idPurchase;
    
    private Integer idProduct;
    
    private String nameProduct;
    
    private int quantity;
       
}
