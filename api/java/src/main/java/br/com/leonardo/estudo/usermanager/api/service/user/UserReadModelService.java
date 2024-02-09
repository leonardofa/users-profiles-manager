package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.api.model.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadModelService {

    private final UserReadEntityService userReadEntityService;
    private final ModelMapper mapper;

    public UserResponse execute(String id) {
        return mapper.map(userReadEntityService.execute(id), UserResponse.class);
    }

}
