package br.com.leonardo.estudo.usermanager.api.model.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileCreationRequest {

  @NotBlank
  private String name;

  private String description;

}
