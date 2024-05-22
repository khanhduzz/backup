package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();

    ProductDto getProduct(Long id);

    ResponseEntity<Product> createProduct(ProductDto dto);

    ResponseEntity<Product> updateProduct(Long id, ProductDto dto);

    ResponseEntity<String> deleteProduct(Long id);

    ResponseEntity<ProductDto> updateProductCategory(Long id, Set<String> category);

    Optional<ProductDto> findProductByName(String name);

    List<Product> findProductByCategory(String categoryName);

    Optional<ProductDto> findFeaturedProduct();
}
