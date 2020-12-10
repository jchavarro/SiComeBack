package com.example.SiCome.model.entity;

import java.io.Serializable;
import javax.persistence.Entity; 
import javax.persistence.Table; 
import javax.persistence.Id; 
import javax.persistence.Column; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oauth_client_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthClientDetails implements Serializable {
   
    private static final long serialVersionUID = 0L;
   
    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;
    
    @Column(name = "resource_ids")
    private String resourceIds;
    
    private String scope; 
	
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes; 
    
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri; 
         
    private String authorities; 
    
    @Column(name = "access_token_validity")
    private String accessTokenValidity;
    
    @Column(name = "refresh_token_validity")
    private String refreshTokenValidity;
    
    @Column(name = "additional_information")
    private String additionalInformation;
    
    private String autoapprove;
}
