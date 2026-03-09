package com.example.cms.model.repository;

import com.example.cms.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<OrderItem, Integer> {
}
