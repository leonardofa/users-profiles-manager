package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.ResourceBlockedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileDeleteService {

  private final ProfileRepository profileRepository;

  @Transactional
  public void execute(String id) {
    try {
      profileRepository.deleteById(id);
      profileRepository.flush();
    } catch (DataIntegrityViolationException | EmptyResultDataAccessException e) {
      throw new ResourceBlockedException("It is not possible delete the profile in use");
    }
  }

}
