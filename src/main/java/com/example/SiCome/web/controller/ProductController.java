/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.web.controller;

import com.example.SiCome.service.ProductService;
import com.example.SiCome.web.dto.ProductDto;
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
@RequestMapping("/api/v.1/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        ProductDto saveProduct = productService.saveProduct(productDto);
        return new ResponseEntity<>(saveProduct, saveProduct != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/")
    public ResponseEntity<?> getProducts(){
        List<ProductDto> products = productService.getProducts();
        return new ResponseEntity(products, products != null && !products.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        boolean isDeleted = productService.deleteProduct(id);
        return new ResponseEntity(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
