package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.CategoryDto;
import nashtech.khanhdu.backend.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();

    List<Category> findByName(String name);

    Category findByNameEquals(String name);

    ResponseEntity<CategoryDto> createCategory(CategoryDto dto);

    ResponseEntity<CategoryDto> updateCategory(Long id, CategoryDto dto);

    ResponseEntity<String> deleteCategory(Long id);
}
