package com.seyanaluong.ecommerce.service;

import com.seyanaluong.ecommerce.configuration.ResourceNotFoundException;
import com.seyanaluong.ecommerce.model.Address;
import com.seyanaluong.ecommerce.model.Customer;
import com.seyanaluong.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address update(@Valid Address address, int id) {
        Address temp = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        address.setId(id);
        address.setCustomer(temp.getCustomer());
        return addressRepository.save(address);
    }

}
