/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.web.controller;

import com.example.SiCome.service.DetailsPurchaseService;
import com.example.SiCome.web.dto.DetailsPurchaseDto;
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
@RequestMapping("/app/v.1/detailsproducts")
public class DetailsPurchaseController {
    
    @Autowired
    private DetailsPurchaseService detailsPurchaseService;
    
    @PostMapping("/")
    public ResponseEntity<?> saveDetailsPurchase(@RequestBody DetailsPurchaseDto detailsPurchaseDto){
        DetailsPurchaseDto saveDetailsPurchase = detailsPurchaseService.saveDetailsPurchase(detailsPurchaseDto);
        return new ResponseEntity(saveDetailsPurchase, saveDetailsPurchase!= null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedDetailsPurchase(@PathVariable("id") int id){
        boolean isDeleted = detailsPurchaseService.deteleDetailsPurchase(id);
        return new ResponseEntity<>(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
