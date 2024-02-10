package br.com.leonardo.estudo.usermanager.api.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSecretChangeRequest {
  @NotBlank
  private String secret;
}
