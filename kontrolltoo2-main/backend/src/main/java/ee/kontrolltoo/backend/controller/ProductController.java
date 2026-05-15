package ee.kontrolltoo.backend.controller;

import ee.kontrolltoo.backend.entity.Product;
import ee.kontrolltoo.backend.repository.ProductRepository;
import ee.kontrolltoo.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {

        if (product.getId() != null) {
            throw new RuntimeException("Cannot add product with id");
        }

        // TOOTE NIMI EI TOHI TÜHI OLLA
        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            throw new RuntimeException("Product title cannot be empty");
        }

        productService.validate(product);

        return productRepository.save(product);
    }

    @PutMapping("products")
    public Product editProduct(@RequestBody Product product) {

        if (product.getId() == null) {
            throw new RuntimeException("Cannot add product without id");
        }


        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            throw new RuntimeException("Product title cannot be empty");
        }

        productService.validate(product);

        return productRepository.save(product);
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}