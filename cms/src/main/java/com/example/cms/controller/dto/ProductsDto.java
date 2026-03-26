package com.example.cms.controller.dto;

import com.example.cms.model.entity.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter

public class ProductsDto {

    private Integer product_id;

    private Integer supplier_id;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    private String size;

    private String color;

    private String variant;

    private Boolean is_bundle;

    private String imageUrl;

    private Boolean active;
}
