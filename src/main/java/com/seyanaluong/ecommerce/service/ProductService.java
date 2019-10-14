package com.seyanaluong.ecommerce.service;


import com.seyanaluong.ecommerce.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    @NotNull Iterable<Product> getAllProducts();

    @NotNull Iterable<Product> getAllProductsByDepartment(String department);

    @NotNull Iterable<Product> getAllProductsByCategory(String category);

    Product getProduct(int id);

    Product create(@Valid Product product);

    Product update(@Valid Product product, int id);

    ResponseEntity delete(int id);

}
