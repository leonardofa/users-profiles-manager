package br.com.leonardo.estudo.usermanager.infrastructure.domain.exception;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;

public class ProfileNotAllowToUseException extends BusinessException {
  public ProfileNotAllowToUseException(ProfileType type) {
    super(String.format("Is not possible use the profile %s", type));
  }

}
