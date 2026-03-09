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
@Table(name = "products")
public class Products {

    @Id
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