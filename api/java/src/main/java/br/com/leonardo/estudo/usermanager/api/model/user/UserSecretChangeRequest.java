package br.com.leonardo.estudo.usermanager.api.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSecretChangeRequest {
    @NotBlank
    private String secret;
}
