package nashtech.khanhdu.backend.services.impl;

import nashtech.khanhdu.backend.dto.CategoryDto;
import nashtech.khanhdu.backend.entities.Category;
import nashtech.khanhdu.backend.exceptions.CategoryNotFoundException;
import nashtech.khanhdu.backend.repositories.CategoryRepository;
import nashtech.khanhdu.backend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = categoryRepository.findByName(name);
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }
        return categories;
    }

    @Override
    public Category findByNameEquals(String name) {
        return categoryRepository.findByNameEquals(name);
    }

    @Override
    public ResponseEntity<CategoryDto> createCategory(CategoryDto dto) {
        List<Category> categories = categoryRepository.findByName(dto.name());
        if (!categories.isEmpty()) {
            throw new CategoryNotFoundException("Category existed");
        }
        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());
        categoryRepository.save(category);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(String name, CategoryDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCategory(Long id) {
        return null;
    }
}
