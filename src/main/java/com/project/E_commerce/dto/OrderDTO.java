
package com.project.E_commerce.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private Long userId;
    private List<OrderItemDTO> items;
    private Double total;

    public Long getId(){ return id;}
    public void setId(Long id){ this.id=id;}
    public Long getUserId(){ return userId;}
    public void setUserId(Long u){ this.userId=u;}
    public List<OrderItemDTO> getItems(){ return items;}
    public void setItems(List<OrderItemDTO> i){ this.items=i;}
    public Double getTotal(){ return total;}
    public void setTotal(Double t){ this.total=t;}
}
