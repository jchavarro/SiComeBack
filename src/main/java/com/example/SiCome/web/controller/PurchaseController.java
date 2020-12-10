/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.web.controller;

import com.example.SiCome.service.ProductService;
import com.example.SiCome.service.PurchaseService;
import com.example.SiCome.web.dto.PurchaseDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v.1/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseService purchaseService;
    
    @PostMapping("/")
    public ResponseEntity<?> savePurchase(@RequestBody PurchaseDto purchaseDto){
        PurchaseDto savePurchase = purchaseService.savePurchase(purchaseDto);
        return new ResponseEntity<>(savePurchase, savePurchase != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/")
    public ResponseEntity getPurchase(){
        List<PurchaseDto> purchases = purchaseService.getPurchases();
        return new ResponseEntity(purchases, purchases != null && !purchases.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);        
    }
    
    @GetMapping("/{status}")
    public ResponseEntity getPurchaseByState(@PathVariable("status") String status){
        List<PurchaseDto> purchases = purchaseService.getPurchasesByState(status);
        return new ResponseEntity(purchases, purchases != null && !purchases.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletedPurchase(@PathVariable("id") int id){
        boolean isDeleted = purchaseService.deletePurchase(id);
        return new ResponseEntity(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
