package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileUpdateRequest;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileUpdateService {

  private final ProfileReadEntityService profileReadEntityService;
  private final ProfileRepository profileRepository;
  private final ModelMapper mapper;

  public ProfileResponse execute(String id, ProfileUpdateRequest request) {
    val entity = profileReadEntityService.execute(id);
    mapper.map(request, entity);
    return mapper.map(profileRepository.save(entity), ProfileResponse.class);
  }

}
