package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.ProfileNotAllowToUseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileReadEntityService {

    private final ProfileRepository repository;

    public Profile execute(String id) {
        val profile = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (ProfileType.ADMINISTRATOR.equals(profile.getType())) {
            throw new ProfileNotAllowToUseException(ProfileType.ADMINISTRATOR);
        }
        return profile;
    }

}
