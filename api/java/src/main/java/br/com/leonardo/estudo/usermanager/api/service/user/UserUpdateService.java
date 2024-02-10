package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.api.model.user.UserUpdateRequest;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

  private final UserReadEntityService userReadEntityService;
  private final UserRepository repository;
  private final ModelMapper mapper;

  public UserResponse execute(String id, UserUpdateRequest request) {
    val entity = userReadEntityService.execute(id);
    mapper.map(request, entity);
    return mapper.map(repository.save(entity), UserResponse.class);
  }

}
