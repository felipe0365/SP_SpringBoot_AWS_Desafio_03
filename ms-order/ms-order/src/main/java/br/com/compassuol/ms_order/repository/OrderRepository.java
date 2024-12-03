package br.com.compassuol.ms_order.repository;

import br.com.compassuol.ms_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
