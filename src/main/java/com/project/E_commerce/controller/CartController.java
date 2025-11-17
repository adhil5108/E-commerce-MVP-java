package com.project.E_commerce.controller;

import com.project.E_commerce.model.*;
import com.project.E_commerce.service.CartService;
import com.project.E_commerce.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{productId}")
    public Cart addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity ) {
        User user = userService.getCurrentUser();
        return cartService.addToCart(user, productId, quantity);
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        User user = userService.getCurrentUser();
        return cartService.getCartItems(user);
    }

    @PutMapping("/update/{itemId}")
    public Cart updateItem(@PathVariable Long itemId, @RequestParam int quantity) {
        User user = userService.getCurrentUser();
        return cartService.updateQuantity(user, itemId, quantity);
    }

    @DeleteMapping("/remove/{itemId}")
    public Cart removeItem(@PathVariable Long itemId) {
        User user = userService.getCurrentUser();
        return cartService.removeFromCart(user, itemId);
    }
}

