package com.example.cms.model.repository;

import com.example.cms.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.order_id = :orderId")
    List<OrderItem> findItemsByOrderId(@Param("orderId") Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_items WHERE product_id = :id", nativeQuery = true)
    void deleteOrderItemBeforeProduct (@Param("id") Integer id);


}
