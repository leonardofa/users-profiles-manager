package br.com.leonardo.estudo.usermanager.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
public class UserProfileResponse {
  private String id;
  private String identification;
  private String name;
}
