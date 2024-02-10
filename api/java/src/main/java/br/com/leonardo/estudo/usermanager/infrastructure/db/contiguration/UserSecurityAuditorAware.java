package br.com.leonardo.estudo.usermanager.infrastructure.db.contiguration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.function.Supplier;


public class UserSecurityAuditorAware implements AuditorAware<String> {

  private final Supplier<String> identification;

  public UserSecurityAuditorAware(Supplier<String> identification) {
    this.identification = identification;
  }

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(identification.get());
  }

}
