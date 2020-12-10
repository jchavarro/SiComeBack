/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service.impl;

import com.example.SiCome.model.entity.Product;
import com.example.SiCome.model.repository.ProductRepository;
import com.example.SiCome.service.ProductService;
import com.example.SiCome.web.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository repository;
    
    private final ModelMapper mapper = new ModelMapper();
            
    @Override
    public ProductDto saveProduct(ProductDto product) {
        if (validateProduct(product)){
            ProductDto productDto = mapper.map(repository.save(mapper.map(product, Product.class)), ProductDto.class);
            return productDto;
        }
        return null;
    }
    
    private boolean validateProduct(ProductDto product){
        return product != null && product.getName() != null  && !product.getName().isEmpty() && product.getCost() != null ;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> productsDto = null;
        List<Product> products = repository.findAll();
        if (products != null && !products.isEmpty() ) {
            productsDto= new ArrayList<>();
            for (Product product : products) {
                productsDto.add(mapper.map(product, ProductDto.class));
            }
        }
                
        return productsDto;
    }

    @Override
    public boolean deleteProduct(int id) {
        Optional<Product> optProduct = repository.findById(id);
        if (optProduct.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false; 
    }
}
