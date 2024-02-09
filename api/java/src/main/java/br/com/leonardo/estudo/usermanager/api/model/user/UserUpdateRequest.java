package br.com.leonardo.estudo.usermanager.api.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    @NotBlank
    private String identification;
    @NotBlank
    private String name;
    private LocalDate born;
    private String contact;
}
