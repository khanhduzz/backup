package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    ProductDto getProduct(Long id);

    ResponseEntity<Product> createProduct(ProductDto dto);

    ResponseEntity<Product> updateProduct(Long id, ProductDto dto);

    ResponseEntity<String> deleteProduct(Long id);

    List<Product> findProductByName(String name);

    List<Product> findProductByCategory(String categoryName);

    List<Product> findFeaturedProduct();
}
