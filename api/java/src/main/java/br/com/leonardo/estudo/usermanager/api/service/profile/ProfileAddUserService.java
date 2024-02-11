package br.com.leonardo.estudo.usermanager.api.service.profile;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
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
  private final ProfileReadModelService profileReadModelService;

  public ProfileResponse execute(String id, String userId) {
    val user = userReadEntityService.execute(userId);
    val userProfile = user.getProfile();
    if (userProfile != null && userProfile.getId().equals(id)) {
      user.setProfile(null);
    } else {
      user.setProfile(userProfileReadEntityService.execute(id));
    }
    userRepository.save(user);
    return profileReadModelService.execute(id);
  }

}
