package br.com.leonardo.estudo.usermanager.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
  public UserNotFoundException() {
    super("User not found", HttpStatus.NOT_FOUND);
  }

}
