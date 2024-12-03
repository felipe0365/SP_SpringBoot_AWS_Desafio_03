package br.com.compassuol.ms_order.client;

import br.com.compassuol.ms_order.model.external.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080/api")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @PatchMapping("/products/{id}/decrease/{quantity}")
    ResponseEntity<Product> decreaseStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity);

}
