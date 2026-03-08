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

    @PutMapping("/inventory/{id}")
    Inventory updateInventory(@RequestBody Inventory inventory, @PathVariable("id") Integer inventoryId) {
        return repository.findById(inventoryId)
                .map(existingInventory -> {
                    existingInventory.setProduct(inventory.getProduct());
                    existingInventory.setShape(inventory.getShape());
                    existingInventory.setSize(inventory.getSize());
                    existingInventory.setStyle(inventory.getStyle());
                    existingInventory.setColor(inventory.getColor());
                    existingInventory.setStock_quantity(inventory.getStock_quantity());
                    existingInventory.setReorder_level(inventory.getReorder_level());
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