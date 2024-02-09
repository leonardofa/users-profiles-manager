package br.com.leonardo.estudo.usermanager.infrastructure.db.repository;

import br.com.leonardo.estudo.usermanager.infrastructure.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
