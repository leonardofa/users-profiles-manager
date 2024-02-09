package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.User;
import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadEntityService {

    private final UserRepository repository;

    public User execute(String id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
