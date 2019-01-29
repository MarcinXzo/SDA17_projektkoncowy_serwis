package sda.java17.zgagamacservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.java17.zgagamacservice.model.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllByNameContainingOrEmailContainingOrSurnameContaining(String name, String email, String surname);

    Optional<AppUser> findByEmail(String email);
}
