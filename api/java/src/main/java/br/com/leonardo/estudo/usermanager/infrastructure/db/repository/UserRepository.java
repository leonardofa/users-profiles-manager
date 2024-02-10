package br.com.leonardo.estudo.usermanager.infrastructure.db.repository;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  List<User> findByProfile(Profile source);

  Optional<User> findByIdentification(String username);

  Integer countByProfile(Profile source);
}
