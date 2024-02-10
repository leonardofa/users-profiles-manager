package br.com.leonardo.estudo.usermanager.api.model.profile;

import br.com.leonardo.estudo.usermanager.api.model._ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id", callSuper = true)
@Data
public class ProfileReduceResponse extends _ResponseBase {
  private String id;
  private String name;
  private String description;
  private Integer usersQuantity;
}
