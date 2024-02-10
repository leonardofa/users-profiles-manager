package br.com.leonardo.estudo.usermanager.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;

public class ProfileNotFoundException extends BusinessException {
  public ProfileNotFoundException() {
    super("Profile not found", HttpStatus.NOT_FOUND);
  }

}
