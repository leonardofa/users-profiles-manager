package br.com.leonardo.estudo.usermanager.infrastructure.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile extends _EntityBase {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProfileType type;

}
