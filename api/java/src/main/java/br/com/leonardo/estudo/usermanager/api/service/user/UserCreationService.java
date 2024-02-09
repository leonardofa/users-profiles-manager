package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.user.UserCreationRequest;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.User;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationService {

    private final UserRepository repository;
    private final UserProfileReadEntityService userProfileReadEntityService;
    private final ModelMapper mapper;

    public UserResponse execute(final UserCreationRequest request) {
        val entity = mapper.map(request, User.class);
        entity.setProfile(userProfileReadEntityService.execute(request.getProfile()));
        return mapper.map(repository.save(entity), UserResponse.class);
    }

}
