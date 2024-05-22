package nashtech.khanhdu.backend.controllers;

import jakarta.annotation.security.RolesAllowed;
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

    @GetMapping("/all")
    public List<Product> getAllProducts () {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct (@Valid @RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,
                                                    @RequestBody ProductDto dto) {
        return productService.updateProduct(productId, dto);
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }
}
