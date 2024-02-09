package br.com.leonardo.estudo.usermanager.infrastructure.modelmapper.converters;

import br.com.leonardo.estudo.usermanager.api.model.profile.ProfileCreationRequest;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.ProfileType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileModelToEntity implements Converter<ProfileCreationRequest, Profile> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {
        mapper.addConverter(this);
    }

    @Override
    public Profile convert(MappingContext<ProfileCreationRequest, Profile> context) {
        val request = context.getSource();
        val entity = new ModelMapper().map(request, context.getDestinationType());
        entity.setType(ProfileType.USER);
        return entity;
    }

}
