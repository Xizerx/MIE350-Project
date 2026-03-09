package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inventory")
public class Inventory {

    @Id
    private Integer inventory_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

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