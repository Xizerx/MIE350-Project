package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

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

    @NotNull
    private Integer stock_quantity; // Kept your existing name for quantityInStock

    private Integer reorder_level;

    private Integer reorderQuantity;

    private String warehouseLocation;

    private Timestamp lastRestocked;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}