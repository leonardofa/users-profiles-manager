package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.service.user.UserProfileReadEntityService;
import br.com.leonardo.estudo.usermanager.api.service.user.UserReadEntityService;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.BusinessException;
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
    try {
      if (ProfileType.ADMINISTRATOR.equals(user.getProfile().getType())) {
        throw new BusinessException("Is not possible change ADMINISTRATOR user profile");
      }
    } catch (final NullPointerException ignored) {
    }
    user.setProfile(userProfileReadEntityService.execute(id));
    userRepository.save(user);
  }

}
