package com.seyanaluong.ecommerce.repository;

import com.seyanaluong.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Iterable<Product> findByDepartment(String department);
    Iterable<Product> findByCategory(String category);
}
