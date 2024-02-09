package br.com.leonardo.estudo.usermanager.api.model.user;

import br.com.leonardo.estudo.usermanager.api.model._ResponseBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(of = "id", callSuper = true)
@Data
public class UserResponse extends _ResponseBase {
    private String id;
    private String identification;
    private String name;
    private LocalDate born;
    private String contact;
    private String profile;
}
