package br.com.leonardo.estudo.usermanager.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;

public class ResourceBlockedException extends BusinessException{
    public ResourceBlockedException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

}
