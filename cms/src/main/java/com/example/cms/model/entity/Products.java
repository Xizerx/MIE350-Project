package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Products {

    @Id
    private Integer product_id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @NotEmpty
    private String name;

    @Column(length = 500)
    private String description;

    @NotEmpty
    private String category;

    private BigDecimal price;

    private String size;

    private String color;

    private String variant;

    @NotNull
    private Boolean is_bundle;

    private String imageUrl;

    private Boolean active = true; // Default to true

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}