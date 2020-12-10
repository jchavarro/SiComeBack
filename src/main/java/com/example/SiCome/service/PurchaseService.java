/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service;

import com.example.SiCome.web.dto.PurchaseDto;
import java.util.List;

public interface PurchaseService {
    
    PurchaseDto savePurchase (PurchaseDto purchase);
    
    List<PurchaseDto> getPurchases();
    
    List<PurchaseDto> getPurchasesByState(String state);
    
    boolean deletePurchase(int id);
    
}
