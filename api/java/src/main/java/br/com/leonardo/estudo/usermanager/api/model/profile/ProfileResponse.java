package br.com.leonardo.estudo.usermanager.api.model.profile;

import br.com.leonardo.estudo.usermanager.api.model._ResponseBase;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = true)
@Data
public class ProfileResponse extends _ResponseBase {
  private String id;
  private String name;
  private String description;
  private List<UserResponse> users;
}
