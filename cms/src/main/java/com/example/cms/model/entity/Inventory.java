package com.example.cms.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventory_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotEmpty
    private String shape;

    @NotEmpty
    private String size;

    private String style;

    private String color;

    @NotNull
    private Integer stock_quantity;

    private Integer reorder_level;
}