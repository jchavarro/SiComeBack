/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.service;

import com.example.SiCome.web.dto.ProductDto;
import java.util.List;


public interface ProductService {
    
    ProductDto saveProduct(ProductDto product);
    
    List<ProductDto> getProducts();
    
    boolean deleteProduct(int id);
}
