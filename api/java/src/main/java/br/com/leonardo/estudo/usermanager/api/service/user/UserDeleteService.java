package br.com.leonardo.estudo.usermanager.api.service.user;

import br.com.leonardo.estudo.usermanager.infrastructure.db.repository.UserRepository;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.exception.ResourceBlockedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository repository;

    @Transactional
    public void execute(String id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException e) {
            throw new ResourceBlockedException("It is not possible delete the user in use/blocked");
        }
    }

}
