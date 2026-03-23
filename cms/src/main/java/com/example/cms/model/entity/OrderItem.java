package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_item_id;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnoreProperties("orderItems")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @NotNull
    private Integer quantity;

    private BigDecimal unit_price;

    private BigDecimal discount = BigDecimal.ZERO;

    private Boolean includesApplicationKit = false;
}