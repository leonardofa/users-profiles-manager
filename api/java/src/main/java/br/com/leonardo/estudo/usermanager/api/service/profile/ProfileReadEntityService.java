package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileReadEntityService {

    private final ProfileRepository repository;

    public Profile execute(String id) {
        return repository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

}
