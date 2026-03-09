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
@Table(name = "suppliers")
public class Supplier {

    @Id
    @NotNull
    private Integer supplierId;

    @NotEmpty
    private String name;

    private String email;

    private String phone;


    public Supplier(Integer supplierId, String name, String email, String phone){
        this.supplierId = supplierId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}
