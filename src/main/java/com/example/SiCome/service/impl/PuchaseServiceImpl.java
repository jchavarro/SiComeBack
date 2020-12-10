/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service.impl;

import com.example.SiCome.model.entity.Purchase;
import com.example.SiCome.model.repository.PurchaseRepository;
import com.example.SiCome.service.PurchaseService;
import com.example.SiCome.web.dto.PurchaseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class PuchaseServiceImpl implements PurchaseService{
    
    @Autowired
    private PurchaseRepository repository;
    
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public PurchaseDto savePurchase(PurchaseDto purchase) {
        PurchaseDto purchaseDto = mapper.map(repository.save(mapper.map(purchase, Purchase.class)), PurchaseDto.class);
        return purchaseDto;
    }

    
    @Override
    public List<PurchaseDto> getPurchases() {
        List<PurchaseDto> purchaseDtos = null;
        List<Purchase> purchases = repository.findAll();
        if (purchases != null && !purchases.isEmpty()) {
            purchaseDtos = new ArrayList<>();
            for (Purchase purchase : purchases) {
                purchaseDtos.add(mapper.map(purchase, PurchaseDto.class));
            }
        }
        return purchaseDtos;
    }

    @Override
    public List<PurchaseDto> getPurchasesByState(String state) {
        List<PurchaseDto> purchaseDtos = null;
        List<Purchase> purchases = repository.findByState(state);
        if (purchases != null && !purchases.isEmpty()) {
            purchaseDtos = new ArrayList<>();
            for (Purchase purchase : purchases) {
                purchaseDtos.add(mapper.map(purchase, PurchaseDto.class));
            }
        }
        return purchaseDtos;
    }

    @Override
    public boolean deletePurchase(int id) {
        Optional<Purchase> optPurchase = repository.findById(id);
        if (optPurchase.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
