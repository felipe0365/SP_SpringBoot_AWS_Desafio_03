package br.com.compassuol.ms_stock.service;

import br.com.compassuol.ms_stock.exception.ProductNotFoundException;
import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static br.com.compassuol.ms_stock.common.ProductConstants.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProduct_WithValidData_ReturnsProduct() {
        when(productService.save(PRODUCT)).thenReturn(PRODUCT);
        // system under test
        Product sut = productService.save(PRODUCT);

        assertEquals(sut, PRODUCT);
    }

    @Test
    public void createProduct_WithInvalidData_ThrowsException() {
        when(productRepository.save(INVALID_PRODUCT)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> productService.save(INVALID_PRODUCT)) ;
    }

    @Test
    public void findProduct_ByExistingId_ReturnsProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(PRODUCT));

        Optional<Product> sut = Optional.ofNullable(productService.findById(1L));

        assertEquals(sut.get(), PRODUCT);
        assertNotNull(sut.get());
    }

    @Test
    public void findProduct_ByInvalidId_ThrowsException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    public void getAllClients_ReturnsAllClients() {
        List<Product> products = new ArrayList<>() {
            {
                add(PRODUCT);
            }
        };
        when(productRepository.findAll()).thenReturn(products);

        List<Product> sut = productService.findAll();

        assertEquals(sut.size(), products.size());
        assertNotNull(sut.get(0));
    }

    @Test
    public void getAllProducts_returnsNoProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> sut = productService.findAll();

        assertEquals(sut.size(), 0);
    }
}
