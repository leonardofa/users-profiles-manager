package br.com.leonardo.estudo.usermanager.api.model;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest (@NotBlank String identification, @NotBlank String secret){
}
