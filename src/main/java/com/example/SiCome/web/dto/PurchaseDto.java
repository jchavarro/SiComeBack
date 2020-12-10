/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SiCome.web.dto;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    
    private Integer id;
    
    private Calendar dateRealization;
    
    private Long idUser;
    
    private String state;
    
    private Integer totalCost;
    
    private String address;
    
    private Long cedulaUser;
    
    private String nombreUser;
    
    private String apellidoUser;
    
    private Long phoneUser;
}
