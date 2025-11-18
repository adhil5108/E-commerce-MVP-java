package com.project.E_commerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderDTO {

    @NotNull(message = "Order ID cannot be null")
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Order items cannot be null")
    @Size(min = 1, message = "Order must contain at least one item")
    @Valid
    private List<OrderItemDTO> items;

    @NotNull(message = "Total amount cannot be null")
    @PositiveOrZero(message = "Total amount cannot be negative")
    private Double total;

    public OrderDTO() {}

    public OrderDTO(Long id, Long userId, List<OrderItemDTO> items, Double total) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
