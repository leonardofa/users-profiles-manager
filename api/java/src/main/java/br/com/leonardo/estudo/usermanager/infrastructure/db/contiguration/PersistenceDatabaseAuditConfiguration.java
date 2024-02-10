package br.com.leonardo.estudo.usermanager.infrastructure.db.contiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userSecurityAuditorAware", dateTimeProviderRef = "offsetDateTimeProvider")
public class PersistenceDatabaseAuditConfiguration {

  @Bean
  public AuditorAware<String> userSecurityAuditorAware() {
    return new UserSecurityAuditorAware();
  }

  @Bean
  public DateTimeProvider offsetDateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now());
  }

}
