package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.service.user.UserProfileReadEntityService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserReadEntityService;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileAddUserService {

    private final UserProfileReadEntityService userProfileReadEntityService;
    private final UserReadEntityService userReadEntityService;
    private final UserRepository userRepository;

    public void execute(String id, String userId) {
        val user = userReadEntityService.execute(userId);
        user.setProfile(userProfileReadEntityService.execute(id));
        userRepository.save(user);
    }

}
