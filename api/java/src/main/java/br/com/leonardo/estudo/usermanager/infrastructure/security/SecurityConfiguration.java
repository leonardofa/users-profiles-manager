package br.com.leonardo.estudo.usermanager.infrastructure.security;


import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorize) ->
            authorize
                .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(antMatcher("/public/**")).permitAll()
                .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    val authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    val providerManager = new ProviderManager(authenticationProvider);
    providerManager.setEraseCredentialsAfterAuthentication(true);
    return providerManager;
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      val user = userRepository.findByIdentification(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      return new User(
          user.getIdentification(),
          user.getSecret(),
          user.getProfile() != null ? List.of(new SimpleGrantedAuthority(user.getProfile().getType().name())) : Collections.emptyList()
      );
    };
  }

}
