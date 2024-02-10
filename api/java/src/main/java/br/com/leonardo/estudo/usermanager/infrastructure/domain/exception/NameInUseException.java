package br.com.leonardo.estudo.usermanager.infrastructure.domain.exception;

public class NameInUseException extends BusinessException {
  public NameInUseException(String name) {
    super(String.format("The name %s is in use", name));
  }

}
