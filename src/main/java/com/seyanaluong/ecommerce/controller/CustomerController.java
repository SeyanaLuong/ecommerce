package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.model.Customer;
import com.seyanaluong.ecommerce.service.AddressService;
import com.seyanaluong.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public String index(Customer customer, Model model, Principal principal) {
        boolean exist = customerService.doesCustomerExist(principal.getName());
        if(exist) {
            model.addAttribute("customer", customerService.getCustomerByUsername(principal.getName()));
            return "customer/customer";
        }
        return  "customer/customer-details";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute @Valid Customer customer, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            return "customer/customer-details";
        }
        model.addAttribute("customer", customerService.create(customer, principal));
        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String updateCustomerForm(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/update-customer-details";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable int id, @Valid Customer customer, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "customer/update-customer-details";
        }
        addressService.update(customer.getAddress(), id);
        model.addAttribute("customer", customerService.update(customer, id));
        if(principal.getName() == "admin") {
            return "redirect:/admin/customer";
        }
        return "redirect:/customer";
    }

}
