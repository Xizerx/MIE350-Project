package com.example.cms.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @NotEmpty
    private String name;

    @NotEmpty
    private String category;

    private BigDecimal price;

    @NotNull
    private Boolean is_bundle;

}