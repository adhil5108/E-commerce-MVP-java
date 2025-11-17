package com.project.E_commerce.repositories;

import com.project.E_commerce.model.Cart;
import com.project.E_commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
