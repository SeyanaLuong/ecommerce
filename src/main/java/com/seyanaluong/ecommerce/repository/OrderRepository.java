package com.seyanaluong.ecommerce.repository;

import com.seyanaluong.ecommerce.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Iterable<Order> findByCustomer(String customer);
}
