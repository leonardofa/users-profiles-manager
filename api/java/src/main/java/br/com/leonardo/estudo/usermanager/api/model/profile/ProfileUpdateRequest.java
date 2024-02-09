package br.com.leonardo.estudo.usermanager.api.model.profile;

import lombok.Data;

@Data
public class ProfileUpdateRequest {

    private String name;
    private String description;
    
}
