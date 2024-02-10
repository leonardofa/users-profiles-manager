package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.user.UserCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.User;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.NameInUseException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationService {

  private final ModelMapper mapper;
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public UserResponse execute(final UserCreationRequest request) {
      try {
          val entity = mapper.map(request, User.class);
          entity.setSecret(passwordEncoder.encode(request.getSecret()));
          return mapper.map(repository.save(entity), UserResponse.class);
      } catch (DataIntegrityViolationException e) {
          if (e.getCause() instanceof ConstraintViolationException cause) {
              if ("user.unique_user".equals(cause.getConstraintName())) {
                  throw new NameInUseException(request.getIdentification());
              }
          }
          throw e;
      }
  }

}
