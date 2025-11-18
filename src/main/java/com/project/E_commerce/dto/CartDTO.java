package com.project.E_commerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CartDTO {

    @NotNull(message = "Cart ID cannot be null")
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Cart items cannot be null")
    @Size(min = 1, message = "Cart must contain at least one item")
    @Valid
    private List<CartItemDTO> items;

    public CartDTO() {}

    public CartDTO(Long id, Long userId, List<CartItemDTO> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<CartItemDTO> getItems() { return items; }
    public void setItems(List<CartItemDTO> items) { this.items = items; }
}
