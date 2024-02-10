package br.com.leonardo.estudo.usermanager.infrastructure.security;

import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

  private final UserRepository userRepository;

  public boolean authUser(String id) {
    val principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    val user = userRepository.findByIdentification(principal.getUsername()).orElse(null);
    return user != null && user.getId().equals(id);
  }

}
