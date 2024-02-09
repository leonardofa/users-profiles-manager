package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCreationService {

    private final ProfileRepository repository;
    private final ModelMapper mapper;

    public ProfileResponse execute(final ProfileCreationRequest request) {
        val entity = repository.save(mapper.map(request, Profile.class));
        return mapper.map(entity, ProfileResponse.class);
    }

}
