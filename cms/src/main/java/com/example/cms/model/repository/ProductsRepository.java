package com.example.cms.model.repository;

import com.example.cms.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findProductByName(String name);

    // NEW: Find active products by category and size
    List<Products> findByCategoryAndSizeAndActiveTrue(String category, String size);
    // NEW: Find all active products
    List<Products> findByActiveTrue();

}