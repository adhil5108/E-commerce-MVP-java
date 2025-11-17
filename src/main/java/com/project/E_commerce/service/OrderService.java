package com.project.E_commerce.service;

import com.project.E_commerce.model.*;
import com.project.E_commerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderItemRepository orderItemRepo;
    @Autowired private CartService cartService;
    @Autowired private UserService userService;
    @Autowired private ProductRepo productRepo;
    @Autowired private CartRepository cartRepo;

    public Order checkout(User user) {
        Cart cart = cartService.getUserCart(user);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot checkout.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        orderRepo.save(order);

        double total = 0.0;

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.getStock() < quantity) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - quantity);
            productRepo.save(product);

            double price = product.getPrice() * quantity;
            total += price;

            OrderItem orderItem = new OrderItem(order, product, quantity, price);
            order.getItems().add(orderItem);
        }

        order.setTotalAmount(total);
        order.setStatus("PAID");
        orderRepo.save(order);


        cart.getItems().clear();
        cartRepo.save(cart);

        return order;
    }

    public List<Order> getUserOrders(User user) {
        return orderRepo.findByUser(user);
    }
}

