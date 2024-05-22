package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
