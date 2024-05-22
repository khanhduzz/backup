package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

    Category findByNameEquals(String name);
}
