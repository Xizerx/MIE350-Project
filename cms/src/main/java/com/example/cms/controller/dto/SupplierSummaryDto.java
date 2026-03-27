package com.example.cms.controller.dto;

import java.math.BigDecimal;

public class SupplierSummaryDto {
    private Integer supplier_id;
    private String name;
    private String email;
    private String phone;
    private Long number_products_supplied;
    private BigDecimal revenue;

    public SupplierSummaryDto(Integer supplier_id, String name, String email, String phone,
                              Long number_products_supplied, BigDecimal revenue) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.number_products_supplied = number_products_supplied;
        this.revenue = revenue != null ? revenue : BigDecimal.ZERO;
    }

    public Integer getSupplier_id() { return supplier_id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Long getNumber_products_supplied() { return number_products_supplied; }
    public BigDecimal getRevenue() { return revenue; }
}