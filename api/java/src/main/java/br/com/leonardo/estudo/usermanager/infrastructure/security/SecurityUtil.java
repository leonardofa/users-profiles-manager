package br.com.leonardo.estudo.usermanager.infrastructure.security;

import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

  private final UserRepository userRepository;


  public boolean authUserIdentification(String identification) {
    val user = getPrincipal();
    return user != null && user.getUsername().equals(identification);
  }

  public boolean authUser(String id) {
    val user = getPrincipal();
    return user != null && user.getId().equals(id);
  }

  public CustomUser getPrincipal() {
    try {
      return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (ClassCastException ignored) {
      return null;
    }
  }

  public String getUserName() {
    try {
      return getPrincipal().getId();
    } catch (final NullPointerException ignored) {
      return "anonymn";
    }
  }

}
