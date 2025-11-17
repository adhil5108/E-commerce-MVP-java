package com.project.E_commerce.controller;

import com.project.E_commerce.model.Order;
import com.project.E_commerce.model.User;
import com.project.E_commerce.service.OrderService;
import com.project.E_commerce.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private UserService userService;

    @PostMapping("/checkout")
    public Order checkout() {
        User user = userService.getCurrentUser();
        return orderService.checkout(user);
    }

    @GetMapping
    public List<Order> getMyOrders() {
        User user = userService.getCurrentUser();
        return orderService.getUserOrders(user);
    }
}

