package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.NameInUseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileCreationService {

  private final ProfileRepository repository;
  private final ModelMapper mapper;

  public ProfileResponse execute(final ProfileCreationRequest request) {
    try {
      val entity = repository.save(mapper.map(request, Profile.class));
      return mapper.map(entity, ProfileResponse.class);
    } catch (DataIntegrityViolationException e) {
      if (e.getCause() instanceof ConstraintViolationException cause) {
        if ("profile.unique_profile".equals(cause.getConstraintName())) {
          throw new NameInUseException(request.getName());
        }
      }
      throw e;
    }
  }

}
