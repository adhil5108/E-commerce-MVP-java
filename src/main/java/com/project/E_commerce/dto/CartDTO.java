
package com.project.E_commerce.dto;

import java.util.List;

public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Long getUserId(){ return userId;}
    public void setUserId(Long u){ this.userId=u;}
    public List<CartItemDTO> getItems(){ return items;}
    public void setItems(List<CartItemDTO> i){ this.items=i;}
}
