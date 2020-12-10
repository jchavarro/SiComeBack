package com.example.SiCome.model.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "sec_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private Long cedula;
    
    private String nombre;
    
    private String apellido;
    
    private String username;
    
    private String password;
    
    private String city;
    
    private String address;
    
    private Long phone;
    
    private String email;
    
    private boolean enabled;
    
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;
    
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
        
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private OAuthClientDetails client;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_role_user", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "cedula")
    }, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")
    }) 
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    
   
}
