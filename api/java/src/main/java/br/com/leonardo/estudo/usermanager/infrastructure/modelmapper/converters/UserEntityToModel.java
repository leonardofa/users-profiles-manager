package br.com.leonardo.estudo.usermanager.infrastructure.modelmapper.converters;

import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityToModel implements Converter<User, UserResponse> {

  private final ModelMapper mapper;

  @PostConstruct
  public void init() {
    mapper.addConverter(this);
  }

  @Override
  public UserResponse convert(MappingContext<User, UserResponse> context) {
    val user = context.getSource();
    val response = new ModelMapper().map(user, UserResponse.class);
    response.setProfile(Optional.ofNullable(user.getProfile()).orElse(new Profile()).getName());
    return response;
  }

}
