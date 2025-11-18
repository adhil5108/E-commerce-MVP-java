package com.project.E_commerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartItemDTO {

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @Positive(message = "Quantity must be at least 1")
    @Min(value = 1,message = "cannot be less than 1 ")
    private int quantity;

    public CartItemDTO() {}

    public CartItemDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
