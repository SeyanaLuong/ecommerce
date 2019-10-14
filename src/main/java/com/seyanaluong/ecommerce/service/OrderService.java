package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    Iterable<Order> getOrderByCustomer(String customer);

    Order create(@Valid Order order);

    ResponseEntity delete(int id);
}
