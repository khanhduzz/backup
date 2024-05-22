package nashtech.khanhdu.backend.services.impl;

import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.entities.Category;
import nashtech.khanhdu.backend.entities.Product;
import nashtech.khanhdu.backend.exceptions.CategoryNotFoundException;
import nashtech.khanhdu.backend.exceptions.ProductNotFoundException;
import nashtech.khanhdu.backend.mapper.ProductMapper;
import nashtech.khanhdu.backend.repositories.ProductRepository;
import nashtech.khanhdu.backend.services.CategoryService;
import nashtech.khanhdu.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        ProductDto dto = productMapper.toDto(product);
        dto.setCategories(new HashSet<>());
        product.getCategories().forEach(category -> dto.getCategories().add(category.getName()));
        return dto;
    }

    @Override
    @Transactional
    public ResponseEntity<ProductDto> createProduct(ProductDto dto) {
        if (productRepository.findByName(dto.getName()).isPresent()){
            throw new ProductNotFoundException("Product already exists");
        }
        if (dto.getId() == null) {
            dto.setId(0L);
        }
        Product product = productMapper.toEntity(dto);
        dto.getCategories()
                .forEach(e -> {
                      Category category = categoryService.findByNameEquals(e);
                      if (category == null) throw new CategoryNotFoundException("Category not found");
                      product.getCategories().add(category);
                });
        productRepository.save(product);
        dto.setId(product.getId());
        return ResponseEntity.ok(dto);
    }

    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        dto.setId(id);
        var updateProduct = productMapper.updateProduct(product, dto);
        updateProduct.setCategories(new HashSet<>());
        dto.getCategories()
                .forEach(e -> {
                    Category category = categoryService.findByNameEquals(e);
                    if (category == null) throw new CategoryNotFoundException("Category not found");
                    updateProduct.getCategories().add(category);
                });
        productRepository.save(updateProduct);
        return ResponseEntity.ok(updateProduct);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductDto> updateProductCategory(Long id, Set<String> category) {
        return null;
    }

    @Override
    public Optional<ProductDto> findProductByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> findProductByCategory(String categoryName) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> findFeaturedProduct() {
        return Optional.empty();
    }
}
