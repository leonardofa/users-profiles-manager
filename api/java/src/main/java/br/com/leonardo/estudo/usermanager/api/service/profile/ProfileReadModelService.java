package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileReduceResponse;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

  public List<ProfileReduceResponse> list() {
    return profileRepository.findAll().stream().map((element) -> mapper.map(element, ProfileReduceResponse.class)).collect(Collectors.toList());
  }

}
