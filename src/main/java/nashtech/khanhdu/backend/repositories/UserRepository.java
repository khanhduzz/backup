package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserDetails> findUserByUsername (String username);

    Optional<User> findOneByUsername(String username);

    Optional<User> findByEmailEquals(String email);
}
