package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    return userRepository.findAll().stream()
        .map((element) -> element.getProfile() != null && ProfileType.ADMINISTRATOR.equals(element.getProfile().getType()) ? null : mapper.map(element, UserResponse.class))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  public UserResponse executeByIdentification(String identification) {
    return mapper.map(userReadEntityService.executeByIdentification(identification), UserResponse.class);
  }

}
