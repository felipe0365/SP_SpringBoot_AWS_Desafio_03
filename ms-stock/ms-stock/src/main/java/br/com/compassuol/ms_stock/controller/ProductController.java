package br.com.compassuol.ms_stock.controller;

import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.repository.ProductRepository;
import br.com.compassuol.ms_stock.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/decrease/{quantity}")
    public ResponseEntity<Product> decreaseProduct(@PathVariable Long id, @PathVariable Integer quantity) {
        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        if (product.isPresent()) {
            int newStock = product.get().getStock() - quantity;
            product.get().setStock(newStock);
            return new ResponseEntity<>(productService.save(product.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/increase/{quantity}")
    public ResponseEntity<Product> increaseProduct(@PathVariable Long id, @PathVariable Integer quantity) {
        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        if (product.isPresent()) {
            int newStock = product.get().getStock() +    quantity;
            product.get().setStock(newStock);
            return new ResponseEntity<>(productService.save(product.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
