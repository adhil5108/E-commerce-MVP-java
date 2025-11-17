
package com.project.E_commerce.dto;

public class OrderItemDTO {
    private Long productId;
    private int quantity;

    public Long getProductId(){ return productId;}
    public void setProductId(Long p){ this.productId=p;}
    public int getQuantity(){ return quantity;}
    public void setQuantity(int q){ this.quantity=q;}
}
