package com.example.cms.model.repository;

import com.example.cms.controller.dto.SupplierSummaryDto;
import com.example.cms.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value = "SELECT " +
            "s.supplier_id, " +
            "s.name, " +
            "s.email, " +
            "s.phone, " +
            "COUNT(DISTINCT p.product_id) AS number_products_supplied, " +
            "COALESCE(SUM(oi.quantity * oi.unit_price), 0) AS revenue " +
            "FROM suppliers s " +
            "LEFT JOIN products p ON s.supplier_id = p.supplier_id " +
            "LEFT JOIN order_items oi ON p.product_id = oi.product_id " +
            "GROUP BY s.supplier_id, s.name, s.email, s.phone " +
            "ORDER BY s.supplier_id",
            nativeQuery = true)
    List<Object[]> findSupplierSummariesRaw();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM suppliers WHERE supplier_id = :supplierId", nativeQuery = true)
    void deleteSupplierAfterProducts (@Param("supplierId") Integer supplierId);
}