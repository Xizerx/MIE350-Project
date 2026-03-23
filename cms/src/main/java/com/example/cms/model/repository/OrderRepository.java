package com.example.cms.model.repository;

import com.example.cms.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // NEW: Find by status
    List<Order> findByStatusIgnoreCase(String status);
    // NEW: Fetch orders within a specific date range for analytics
    List<Order> findByCreatedAtBetween(java.sql.Timestamp startDate, java.sql.Timestamp endDate);
}