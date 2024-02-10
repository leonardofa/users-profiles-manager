package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserReadModelService {

  private final UserReadEntityService userReadEntityService;
  private final UserRepository userRepository;
  private final ModelMapper mapper;

  public UserResponse execute(String id) {
    return mapper.map(userReadEntityService.execute(id), UserResponse.class);
  }

  public List<UserResponse> list() {
    return userRepository.findAll().stream().map((element) -> mapper.map(element, UserResponse.class)).collect(Collectors.toList());
  }

  public UserResponse executeByIdentification(String identification) {
    return mapper.map(userReadEntityService.executeByIdentification(identification), UserResponse.class);
  }

}
