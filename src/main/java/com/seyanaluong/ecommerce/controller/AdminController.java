package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.model.Customer;
import com.seyanaluong.ecommerce.model.Order;
import com.seyanaluong.ecommerce.model.Product;
import com.seyanaluong.ecommerce.service.CustomerService;
import com.seyanaluong.ecommerce.service.OrderService;
import com.seyanaluong.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order")
    public String orderIndex(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders";
    }

    @GetMapping("order/remove/{id}")
    public String removeOrder(@PathVariable int id) {
        orderService.delete(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/customer")
    public String customerIndex(Customer customer, Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers";
    }

    @GetMapping("/customer/remove/{id}")
    public String removeCustomer(@PathVariable int id, Model model) {
        customerService.delete(id);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers";
    }

    @GetMapping("/product")
    public String productIndex(Product product, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product";
    }

    @PostMapping("/product")
    public String createProduct(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            return "admin/product";
        }
        productService.create(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProductForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "admin/update-product";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable int id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "admin/update-product";
        }
        productService.update(product, id);
        model.addAttribute("products", productService.getAllProducts());
        return "redirect:/admin/product";
    }

    @GetMapping("/product/remove/{id}")
    public String removeProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }

}
