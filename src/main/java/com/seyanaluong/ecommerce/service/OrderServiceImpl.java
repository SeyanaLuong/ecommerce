package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.configuration.ResourceNotFoundException;
import com.seyanaluong.ecommerce.model.Order;
import com.seyanaluong.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public @NotNull Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<Order> getOrderByCustomer(String customer) {
        Iterable<Order> order = orderRepository.findByCustomer(customer);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found");
        }
        return order;
    }

    @Override
    public Order create(@Valid Order order) {
        return orderRepository.save(order);
    }


    @Override
    public ResponseEntity delete(int id) {
        orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Order not found")));
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
