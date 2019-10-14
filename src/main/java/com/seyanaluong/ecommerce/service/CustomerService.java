package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Validated
public interface CustomerService {

    boolean doesCustomerExist(String username);

    Customer getCustomerByUsername(String username);

    @NotNull Iterable<Customer> getAllCustomers();

    Customer getCustomer(int id);

    Customer create(@Valid Customer customer, Principal principal);

    Customer update(@Valid Customer customer, int id);

    ResponseEntity delete(int id);
}
