package br.com.leonardo.estudo.usermanager.infrastructure.modelmapper.converters;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileReduceResponse;
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
public class ProfileEntityToReduceModel implements Converter<Profile, ProfileReduceResponse> {

  private final UserRepository userRepository;

  private final ModelMapper mapper;

  @PostConstruct
  public void init() {
    mapper.addConverter(this);
  }

  @Override
  public ProfileReduceResponse convert(MappingContext<Profile, ProfileReduceResponse> context) {
    val source = context.getSource();
    val target = new ModelMapper().map(source, context.getDestinationType());
    target.setUsersQuantity(userRepository.countByProfile(source));
    return target;
  }

}
