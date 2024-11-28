package br.com.compassuol.ms_stock.repository;

import static br.com.compassuol.ms_stock.common.ProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.util.QueryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductsRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach() {
        PRODUCT.setId(null);
    }

    @Test
    public void createProduct_WithValidData_ReturnsClient() {
        Product product = productRepository.save(PRODUCT);

        Product sut = testEntityManager.find(Product.class, product.getId());

        assertNotNull(sut);
        assertEquals(sut.getName(), product.getName());
        assertEquals(sut.getStock(), product.getStock());
    }

    @Test
    public void createProduct_WithInvalidData_ThrowsException() {
        Product invalidProduct = new Product("", 0);
        assertThrows(RuntimeException.class, () -> productRepository.save(invalidProduct));
    }

    @Test
    public void createProduct_WithExistingName_throwsException() {
        Product product = testEntityManager.persistFlushFind(PRODUCT);
        testEntityManager.detach(product);
        product.setId(null);

        assertThrows(RuntimeException.class, () -> productRepository.save(product));
    }

    @Test
    public void getProduct_ByExistingId_ReturnsProduct() {
        Product product = testEntityManager.persistFlushFind(PRODUCT);

        Optional<Product> clientOptional = productRepository.findById(product.getId());

        assertNotNull(clientOptional);
        assertEquals(clientOptional.get(), product);
    }

    @Test
    public void getProduct_ByUnexistingId_ReturnsClient() {
        Optional<Product> client = productRepository.findById(1L);

        assertTrue(client.isEmpty());
    }

    @Test
    public void listProducts_ReturnsAllProducts() {
        productRepository.save(new Product("p 1", 5));
        productRepository.save(new Product("p 2", 5));
        productRepository.save(new Product("p 3", 5));

        List<Product> response = productRepository.findAll();

        assertNotNull(response);
        assertEquals(response.size(), 3);
    }

    @Test
    public void listClients_ReturnsNoClients() {
        Example<Product> query = QueryBuilder.makeQuery(new Product());

        List<Product> response = productRepository.findAll(query);

        assertTrue(response.isEmpty());
    }
}
