package com.example.cms.model.repository;

import com.example.cms.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.stock_quantity <= i.reorder_level")
    List<Inventory> findLowStockItems();

    // FIXED: Using a custom query bypasses the naming convention issue
    @Query("SELECT i FROM Inventory i WHERE i.product.product_id = :productId")
    Optional<Inventory> findByProductId(@Param("productId") Integer productId);
}