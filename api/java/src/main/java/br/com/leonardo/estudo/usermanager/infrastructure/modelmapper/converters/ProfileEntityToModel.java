package br.com.leonardo.estudo.usermanager.infrastructure.modelmapper.converters;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileResponse;
import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileEntityToModel implements Converter<Profile, ProfileResponse> {

  private final UserRepository userRepository;

  private final ModelMapper mapper;

  @PostConstruct
  public void init() {
    mapper.addConverter(this);
  }

  @Override
  public ProfileResponse convert(MappingContext<Profile, ProfileResponse> context) {
    val source = context.getSource();
    val target = new ModelMapper().map(source, context.getDestinationType());
    target.setUsers(userRepository.findByProfile(source).stream().map((element) -> mapper.map(element, UserResponse.class)).toList());
    return target;
  }

}
