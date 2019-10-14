package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.configuration.ResourceNotFoundException;
import com.seyanaluong.ecommerce.model.Customer;
import com.seyanaluong.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean doesCustomerExist(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null) {
            return false;
        }
        return true;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return customerRepository.findByUsername(username);
    }

    @Override
    public @NotNull Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public Customer create(@Valid Customer customer, Principal principal) {
        customer.setUsername(principal.getName());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(@Valid Customer customer, int id) {
        Customer temp = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setUsername(temp.getUsername());
        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity delete(int id) {
        customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Customer not found")));
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
