package br.com.compassuol.ms_stock.controller;

import br.com.compassuol.ms_stock.exception.ProductNotFoundException;
import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.repository.ProductRepository;
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

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }
}
