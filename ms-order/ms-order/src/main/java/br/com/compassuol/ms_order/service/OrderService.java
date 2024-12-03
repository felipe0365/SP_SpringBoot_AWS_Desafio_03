package br.com.compassuol.ms_order.service;

import br.com.compassuol.ms_order.client.ProductClient;
import br.com.compassuol.ms_order.exception.ProductOutOfStockException;
import br.com.compassuol.ms_order.model.Order;
import br.com.compassuol.ms_order.model.external.Product;
import br.com.compassuol.ms_order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Order createOrder(Long id, Order order) {
        Product product = productClient.getProduct(id).getBody();
        assert product != null;
        if(product.stock() > 1) {
            productClient.decreaseStock(id, product.stock());
            return orderRepository.save(order);
        }else {
            throw new ProductOutOfStockException("Product out of stock");
        }
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
