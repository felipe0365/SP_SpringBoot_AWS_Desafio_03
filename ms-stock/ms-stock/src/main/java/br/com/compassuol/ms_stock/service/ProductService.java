package br.com.compassuol.ms_stock.service;

import br.com.compassuol.ms_stock.exception.ProductNotFoundException;
import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

}
