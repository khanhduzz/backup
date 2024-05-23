package nashtech.khanhdu.backend.controllers;

import jakarta.validation.Valid;
import nashtech.khanhdu.backend.dto.CategoryDto;
import nashtech.khanhdu.backend.entities.Category;
import nashtech.khanhdu.backend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CategoryDto dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }
}
