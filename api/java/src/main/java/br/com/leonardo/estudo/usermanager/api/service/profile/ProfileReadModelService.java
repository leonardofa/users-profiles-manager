package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileReadModelService {

  private final ProfileReadEntityService profileReadEntityService;
  private final ProfileRepository profileRepository;
  private final ModelMapper mapper;

  public ProfileResponse execute(String id) {
    return mapper.map(profileReadEntityService.execute(id), ProfileResponse.class);
  }

  public List<ProfileResponse> list() {
    return profileRepository.findAll().stream()
        .map((element) -> ProfileType.ADMINISTRATOR.equals(element.getType()) ? null : mapper.map(element, ProfileResponse.class))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

}
