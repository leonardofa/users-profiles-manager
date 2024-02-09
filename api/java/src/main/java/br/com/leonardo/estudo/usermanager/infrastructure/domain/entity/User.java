package br.com.leonardo.estudo.usermanager.infrastructure.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class User extends _EntityBase {

    private String identification;

    private String name;

    private String secret;

    @ManyToOne
    private Profile profile;

    private String contact;

    private LocalDate born;

}
