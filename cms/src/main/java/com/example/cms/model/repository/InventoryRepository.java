package com.example.cms.model.repository;

import com.example.cms.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.stock_quantity <= i.reorder_level")
    List<Inventory> findLowStockItems();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM inventory WHERE product_id = :id", nativeQuery = true)
    void deleteInventoryBeforeProduct (@Param("id") Integer id);

    // FIXED: Using a custom query bypasses the naming convention issue
    @Query("SELECT i FROM Inventory i WHERE i.product.product_id = :productId")
    Optional<Inventory> findByProductId(@Param("productId") Integer productId);
}