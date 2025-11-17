package com.project.E_commerce.service;

import com.project.E_commerce.model.*;
import com.project.E_commerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserService userService;


    public Cart getUserCart(User user) {
        return cartRepo.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart(user);
            return cartRepo.save(newCart);
        });
    }


    public Cart addToCart(User user, Long productId, int quantity) {
        Cart cart = getUserCart(user);
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepo.save(cart);
            }
        }

        CartItem newItem = new CartItem(cart, product, quantity);
        cart.getItems().add(newItem);
        return cartRepo.save(cart);
    }


    public Cart removeFromCart(User user, Long itemId) {
        Cart cart = getUserCart(user);
        cart.getItems().removeIf(i -> i.getId().equals(itemId));
        return cartRepo.save(cart);
    }


    public Cart updateQuantity(User user, Long itemId, int quantity) {
        Cart cart = getUserCart(user);
        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
            }
        }
        return cartRepo.save(cart);
    }


    public List<CartItem> getCartItems(User user) {
        return getUserCart(user).getItems();
    }
}

