package com.example.cms.controller;

import com.example.cms.model.entity.Inventory;
import com.example.cms.model.entity.Order;
import com.example.cms.model.entity.OrderItem;
import com.example.cms.model.repository.InventoryRepository;
import com.example.cms.model.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OrderController {

    private final OrderRepository repository;
    private final InventoryRepository inventoryRepository; // NEW

    // NEW: Inject InventoryRepository
    public OrderController(OrderRepository repository, InventoryRepository inventoryRepository) {
        this.repository = repository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/orders")
    List<Order> retrieveAllOrders() {
        return repository.findAll();
    }

    @GetMapping("/orders/{id}")
    Order retrieveOrder(@PathVariable("id") Integer orderId) {
        return repository.findById(orderId).orElse(null);
    }

    @GetMapping("/orders/status/{status}")
    List<Order> retrieveOrdersByStatus(@PathVariable("status") String status) {
        return repository.findByStatusIgnoreCase(status);
    }

    @PostMapping("/orders")
    Order createOrder(@RequestBody Order order) {
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        order.setStatus("PENDING");

        BigDecimal subtotal = BigDecimal.ZERO;

        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);

                // --- NEW: INVENTORY CHECK & DECREMENT ---
                if (item.getProduct() != null) {
                    // Find the inventory record for this specific product
                    Inventory productInventory = inventoryRepository.findAll().stream()
                            .filter(inv -> inv.getProduct().getProduct_id().equals(item.getProduct().getProduct_id()))
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory record not found for product ID: " + item.getProduct().getProduct_id()));

                    // Check if we have enough stock
                    if (productInventory.getStock_quantity() < item.getQuantity()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Out of stock for product: " + item.getProduct().getName() + ". Only " + productInventory.getStock_quantity() + " left.");
                    }

                    // Decrement the primary product stock
                    productInventory.setStock_quantity(productInventory.getStock_quantity() - item.getQuantity());
                    inventoryRepository.save(productInventory);

                    // Bundle Tracking Logic: If it includes an application kit, decrement a generic kit inventory here
                    // (Note: To fully implement this, you would create a dedicated "Application Kit" product and decrement its specific inventory ID in the same way).
                }
                // ----------------------------------------

                if (item.getUnit_price() != null && item.getQuantity() != null) {
                    BigDecimal itemTotal = item.getUnit_price().multiply(new BigDecimal(item.getQuantity()));
                    subtotal = subtotal.add(itemTotal);
                }
            }
        }

        order.setSubtotal(subtotal);

        BigDecimal taxRate = new BigDecimal("0.13");
        BigDecimal tax = subtotal.multiply(taxRate);
        order.setTax(tax);

        BigDecimal shipping = order.getShippingCost() != null ? order.getShippingCost() : BigDecimal.ZERO;
        order.setShippingCost(shipping);
        order.setTotalAmount(subtotal.add(tax).add(shipping));

        return repository.save(order);
    }

    // ... Keep your existing PUT and DELETE methods down here ...

    // FIXED: Replaced the broken PUT /orders/{id} with the proper Status Update endpoint
    @PutMapping("/orders/{id}/status")
    Order updateOrderStatus(@PathVariable("id") Integer orderId, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");

        return repository.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setStatus(newStatus);

                    // Auto-set timestamps based on status progression
                    if ("SHIPPED".equalsIgnoreCase(newStatus)) {
                        existingOrder.setShippedAt(new Timestamp(System.currentTimeMillis()));
                    } else if ("DELIVERED".equalsIgnoreCase(newStatus)) {
                        existingOrder.setDeliveredAt(new Timestamp(System.currentTimeMillis()));
                    }

                    return repository.save(existingOrder);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable("id") Integer orderId) {
        // Find the order before deleting it
        repository.findById(orderId).ifPresent(order -> {
            // Restore inventory for each item
            if (order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getProduct() != null) {
                        inventoryRepository.findAll().stream()
                                .filter(inv -> inv.getProduct().getProduct_id().equals(item.getProduct().getProduct_id()))
                                .findFirst()
                                .ifPresent(productInventory -> {
                                    // Add the stock back!
                                    productInventory.setStock_quantity(productInventory.getStock_quantity() + item.getQuantity());
                                    inventoryRepository.save(productInventory);
                                });
                    }
                }
            }
            // Now safely delete the order
            repository.deleteById(orderId);
        });

    }

}

