package com.example.cms.model.repository;

import com.example.cms.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findProductByName(String name);

    //NEW: Find active products by category, size, and style
    List<Products> findByCategoryAndSizeAndVariantAndActiveTrue(String category, String size, String variant);

    // NEW: Find all active products
    List<Products> findByActiveTrue();

    @Query("SELECT p FROM Products p WHERE p.supplier.supplier_id = :supplierId")
    List<Products> findProductsBySupplier(@Param("supplierId") Integer supplierId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products WHERE product_id = :id", nativeQuery = true)
    void deleteProductAfterInventory (@Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE FROM products WHERE supplier_id = :id", nativeQuery = true)
    void deleteProductBeforeSupplier (@Param("id") Integer id);

}