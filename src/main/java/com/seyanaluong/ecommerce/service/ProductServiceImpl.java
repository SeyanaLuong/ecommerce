package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.configuration.ResourceNotFoundException;
import com.seyanaluong.ecommerce.model.Product;
import com.seyanaluong.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public @NotNull Iterable<Product> getAllProductsByDepartment(String department) {
        return productRepository.findByDepartment(department);
    }

    @Override
    public @NotNull Iterable<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public @NotNull Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product create(@Valid Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(@Valid Product product, int id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productRepository.save(product);
    }

    @Override
    public ResponseEntity delete(int id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Product not found")));
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
