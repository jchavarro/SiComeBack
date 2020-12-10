/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service.impl;

import com.example.SiCome.model.entity.DetailsPurchase;
import com.example.SiCome.model.repository.DetailsPurchaseRepository;
import com.example.SiCome.service.DetailsPurchaseService;
import com.example.SiCome.web.dto.DetailsPurchaseDto;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsPurchaseServiceImpl implements DetailsPurchaseService {

    @Autowired
    private DetailsPurchaseRepository repository;
    
    private final ModelMapper mapper = new ModelMapper();
    
    @Override
    public DetailsPurchaseDto saveDetailsPurchase(DetailsPurchaseDto detailsPurchase) {
        if (validateDetailsPurchase(detailsPurchase)) {
            DetailsPurchaseDto detailsPurchaseDto = mapper.map(repository.save(mapper.map(detailsPurchase, DetailsPurchase.class)), DetailsPurchaseDto.class);
        }
        return null;
    }

    private boolean validateDetailsPurchase(DetailsPurchaseDto detailsPurchase){
        return detailsPurchase != null && detailsPurchase.getIdProduct() != null;
    }
    
    @Override
    public boolean deteleDetailsPurchase(int id) {
        Optional<DetailsPurchase> optDetailsPurchase = repository.findById(id);
        if (optDetailsPurchase.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
