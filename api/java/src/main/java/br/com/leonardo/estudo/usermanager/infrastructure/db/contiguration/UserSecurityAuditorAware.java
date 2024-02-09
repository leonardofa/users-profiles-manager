package br.com.leonardo.estudo.usermanager.infrastructure.db.contiguration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class UserSecurityAuditorAware implements AuditorAware<String> {

    //TODO: when add security do this by SecurityContextHolder
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("anonym");
    }

}
