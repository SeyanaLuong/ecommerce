package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.model.CartItem;
import com.seyanaluong.ecommerce.model.Order;
import com.seyanaluong.ecommerce.service.CustomerService;
import com.seyanaluong.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getOrderByCustomer(principal.getName()));
        return "customer/customer-orders";
    }

    @PostMapping
    public String createOrder(@ModelAttribute @Valid Order order, BindingResult result, HttpSession session, Principal principal, Model model) {
        if (session.getAttribute("cart") == null) {
            return "redirect:/index";
        }
        if (result.hasErrors()) {
            model.addAttribute("customer", customerService.getCustomerByUsername(principal.getName()));
            model.addAttribute("order", order);
            return "cart/checkout";
        }
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        List<CartItem> tempCartItem = new ArrayList<>();
        tempCartItem.addAll(cart.values());

        order.setCartItem(tempCartItem);
        order.setDate(new Date());
        order.setCustomer(principal.getName());
        orderService.create(order);

        session.setAttribute("cart", null);
        return "redirect:/order";
    }

}
