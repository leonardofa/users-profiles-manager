package br.com.leonardo.estudo.usermanager.api.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    @NotBlank
    private String identification;
    @NotBlank
    private String name;
    @Past
    private LocalDate born;
    private String contact;
    @NotNull
    private String profile;
    @NotBlank
    private String secret;
}
