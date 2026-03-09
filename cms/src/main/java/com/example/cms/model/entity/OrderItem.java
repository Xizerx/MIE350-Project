package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @NotNull
    private Integer orderItemId;

    @NotEmpty
    private String shape;

    @NotEmpty
    private String size;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


    public OrderItem(Integer orderItemId, String shape, String size, Integer quantity, Double unitPrice, Order order, Product product){
        this.orderItemId = orderItemId;
        this.shape = shape;
        this.size = size;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderId = order;
        this.productId = product;
    }

}
