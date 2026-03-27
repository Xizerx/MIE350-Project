

package com.example.cms.controller;

import com.example.cms.model.entity.Inventory;
import com.example.cms.model.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class InventoryController {

    private final InventoryRepository repository;

    public InventoryController(InventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/inventory")
    List<Inventory> retrieveAllInventory() {
        return repository.findAll();
    }

    @PostMapping("/inventory")
    Inventory createInventory(@RequestBody Inventory inventory) {
        return repository.save(inventory);
    }

    @GetMapping("/inventory/{id}")
    Inventory retrieveInventory(@PathVariable("id") Integer inventoryId) {
        return repository.findById(inventoryId).orElse(null);
    }
    // NEW: Low stock endpoint
    @GetMapping("/inventory/low-stock")
    List<Inventory> getLowStockInventory() {
        return repository.findLowStockItems();
    }
    // FIXED: Calling the updated repository method
    @GetMapping("/inventory/product/{productId}")
    public Inventory getInventoryByProductId(@PathVariable("productId") Integer productId) {
        return repository.findByProductId(productId).orElse(null);
    }

    @PutMapping("/inventory/{id}")
    Inventory updateInventory(@RequestBody Inventory inventory, @PathVariable("id") Integer inventoryId) {
        return repository.findById(inventoryId)
                .map(existingInventory -> {
                    existingInventory.setProduct(inventory.getProduct());
                    existingInventory.setStock_quantity(inventory.getStock_quantity());
                    existingInventory.setReorder_level(inventory.getReorder_level());
                    existingInventory.setReorderQuantity(inventory.getReorderQuantity());
                    existingInventory.setWarehouseLocation(inventory.getWarehouseLocation());
                    existingInventory.setLastRestocked(inventory.getLastRestocked());
                    return repository.save(existingInventory);
                })
                .orElseGet(() -> {
                    inventory.setInventory_id(inventoryId);
                    return repository.save(inventory);
                });
    }

    @DeleteMapping("/inventory/{id}")
    void deleteInventory(@PathVariable("id") Integer inventoryId) {
        repository.deleteById(inventoryId);
    }
}