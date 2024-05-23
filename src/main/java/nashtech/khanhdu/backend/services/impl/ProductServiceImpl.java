package nashtech.khanhdu.backend.services.impl;

import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.dto.SortedDto;
import nashtech.khanhdu.backend.entities.Category;
import nashtech.khanhdu.backend.entities.Product;
import nashtech.khanhdu.backend.exceptions.CategoryNotFoundException;
import nashtech.khanhdu.backend.exceptions.ProductNotFoundException;
import nashtech.khanhdu.backend.exceptions.SearchingContentIsNotValid;
import nashtech.khanhdu.backend.mapper.ProductMapper;
import nashtech.khanhdu.backend.repositories.ProductRepository;
import nashtech.khanhdu.backend.services.CategoryService;
import nashtech.khanhdu.backend.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {

    private final String mess = "Product not found";

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
                .orElseThrow(() -> new ProductNotFoundException(mess));
        ProductDto dto = productMapper.toDto(product);
        dto.setCategories(new HashSet<>());
        product.getCategories().forEach(category -> dto.getCategories().add(category.getName()));
        return dto;
    }

    @Override
    @Transactional
    public ResponseEntity<Product> createProduct(ProductDto dto) {
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
        return ResponseEntity.ok(product);
    }

    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(mess));
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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(mess));
        productRepository.delete(product);
        return ResponseEntity.ok("Delete product successfully");
    }

    @Override
    public List<Product> findProductByName(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new SearchingContentIsNotValid("Searching is not valid");
        }
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<Product> findProductByCategory(String categoryName) {
        return productRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public List<Product> findFeaturedProduct() {
        return productRepository.findAllByFeaturedEquals(1);
    }

    @Override
    public Page<Product> getAllProductSortedBy(SortedDto dto) {
        int page = dto.page() == null ? 0 : dto.page();
        int number = dto.number() == null ? 20 : dto.number();
        String type = dto.sortedBy();
        int direction = dto.direction() == null ? 1 : dto.direction();
        Pageable sorted;
        if (direction == -1) {
            sorted = PageRequest.of(page,number, Sort.by(type).ascending());
        } else {
            sorted = PageRequest.of(page,number, Sort.by(type).descending());
        }
        return productRepository.findAll(sorted);
    }
}
