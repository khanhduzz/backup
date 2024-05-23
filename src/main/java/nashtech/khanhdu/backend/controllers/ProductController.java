package nashtech.khanhdu.backend.controllers;

import jakarta.validation.Valid;
import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.entities.Product;
import nashtech.khanhdu.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts () {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct (@Valid @RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/update/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                                    @RequestBody ProductDto dto) {
        return productService.updateProduct(productId, dto);
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> findProductByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.findProductByCategory(categoryName);
    }

    @GetMapping("/search/{productName}")
    public List<Product> findProductByName(@PathVariable("productName") String productName) {
        return productService.findProductByName(productName);
    }

    @GetMapping("/feature")
    public List<Product> findFeaturedProduct() {
        return productService.findFeaturedProduct();
    }
}
