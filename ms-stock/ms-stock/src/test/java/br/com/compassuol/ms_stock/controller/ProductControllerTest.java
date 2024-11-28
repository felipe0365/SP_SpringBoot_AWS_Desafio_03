package br.com.compassuol.ms_stock.controller;

import br.com.compassuol.ms_stock.model.Product;
import br.com.compassuol.ms_stock.repository.ProductRepository;
import br.com.compassuol.ms_stock.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.compassuol.ms_stock.common.ProductConstants.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    public void createProduct_WithValidData_ReturnsCreate() throws Exception {
        when(productService.save(PRODUCT)).thenReturn(PRODUCT);

        mockMvc.perform(post("/api/products").content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(PRODUCT));

    }

    @Test
    public void createProduct_WithInvalidData_ReturnsBadRequest() throws Exception {
        Product emptyClient = new Product("", 5);

        mockMvc.perform(post("/api/products").content(objectMapper.writeValueAsString(emptyClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createProduct_WithExistingName_ReturnsConflict() throws Exception {
        when(productService.save(any())).thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(post("/api/products").content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getProduct_ByExistingId_ReturnsProduct() throws Exception {
        when(productService.findById(1L)).thenReturn(PRODUCT);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(PRODUCT));
    }

    @Test
    public void getProduct_ByUnexistingId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listProducts_ReturnsAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(PRODUCTS);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void listProducts_ReturnsNoProducts() throws Exception {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}