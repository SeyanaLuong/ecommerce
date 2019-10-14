package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.model.Address;
import com.seyanaluong.ecommerce.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface AddressService {
    Address update(@Valid Address address, int id);
}
