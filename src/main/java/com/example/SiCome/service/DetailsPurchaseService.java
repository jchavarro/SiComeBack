/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service;

import com.example.SiCome.web.dto.DetailsPurchaseDto;


public interface DetailsPurchaseService {
    
    DetailsPurchaseDto saveDetailsPurchase (DetailsPurchaseDto detailsPurchase);
    
    boolean deteleDetailsPurchase(int id);
}
