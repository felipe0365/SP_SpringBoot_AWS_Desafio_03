package br.com.compassuol.ms_stock.repository;

import br.com.compassuol.ms_stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
