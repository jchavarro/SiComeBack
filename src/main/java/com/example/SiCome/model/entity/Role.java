package com.example.SiCome.model.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sec_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   
    
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_role_permission", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "permission_id", referencedColumnName = "id")
    })
    @OrderBy("name ASC")
    private List<Permission> permissions;
    
    
}
