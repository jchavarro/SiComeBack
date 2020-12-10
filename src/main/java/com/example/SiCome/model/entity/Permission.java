package com.example.SiCome.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sec_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    
    private static final long serialVersionUID = 0L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    
}
