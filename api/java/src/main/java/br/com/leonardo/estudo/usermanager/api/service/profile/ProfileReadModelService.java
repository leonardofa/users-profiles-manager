package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileReadModelService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ProfileResponse execute(String id) {
        val entity = profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
        return mapper.map(entity, ProfileResponse.class);
    }

}
