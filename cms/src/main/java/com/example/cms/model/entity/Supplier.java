package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "suppliers")
public class Supplier {

    @Id
    @NotNull
    private Integer supplier_id;

    @NotEmpty
    private String name;

    private String email;

    private String phone;

    private Integer number_products_supplied;

    private Double revenue;


    public Supplier(Integer supplier_id, String name, String email, String phone, Integer number_products_supplied, Double revenue){
        this.supplier_id = supplier_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.number_products_supplied = number_products_supplied;
        this.revenue = revenue;
    }

}
