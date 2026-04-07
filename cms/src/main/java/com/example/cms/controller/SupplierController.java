package com.example.cms.controller;

import com.example.cms.controller.dto.SupplierSummaryDto;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;
import com.example.cms.controller.exceptions.SupplierNotFoundException;
import com.example.cms.model.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
public class SupplierController {
    @Autowired
    private final SupplierRepository repository;
    private final ProductsRepository productsRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderItemRepository orderItemRepository;

    public SupplierController(SupplierRepository repository, ProductsRepository productsRepository, InventoryRepository inventoryRepository, OrderItemRepository orderItemRepository) {
        this.repository = repository;
        this.productsRepository = productsRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/suppliers")
    public List<SupplierSummaryDto> getSuppliers() {
        return repository.findSupplierSummariesRaw().stream()
                .map(row -> new SupplierSummaryDto(
                        (Integer) row[0],
                        (String) row[1],
                        (String) row[2],
                        (String) row[3],
                        ((Number) row[4]).longValue(),
                        row[5] != null ? new java.math.BigDecimal(row[5].toString()) : java.math.BigDecimal.ZERO
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/suppliers/{supplierId}")
    Supplier retrieveSupplier(@PathVariable("supplierId") Integer supplierId) {
        return repository.findById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

    @PostMapping("/suppliers")
    Supplier createSupplier(@RequestBody Supplier newSupplier) {
        return repository.save(newSupplier);
    }

    @PutMapping("/suppliers/{supplierId}")
    Supplier updateSupplier(@RequestBody Supplier newSupplier, @PathVariable Integer supplierId) {

        return repository.findById(supplierId)
                .map(supplier -> {
                    supplier.setName(newSupplier.getName());
                    supplier.setEmail(newSupplier.getEmail());
                    supplier.setPhone(newSupplier.getPhone());
                    supplier.setNumber_products_supplied(newSupplier.getNumber_products_supplied());
                    supplier.setRevenue(newSupplier.getRevenue());
                    return repository.save(supplier);
                })
                .orElseGet(() -> {
                    newSupplier.setSupplier_id(supplierId);
                    return repository.save(newSupplier);
                });
    }

    @Transactional
    @DeleteMapping("/suppliers/{supplierId}")
    public void deleteSupplier(@PathVariable Integer supplierId) {

        List<Products> products = productsRepository.findProductsBySupplier(supplierId);

        for (Products product : products) {
            Integer productId = product.getProduct_id();
            inventoryRepository.deleteInventoryBeforeProduct(productId);
            orderItemRepository.deleteOrderItemBeforeProduct(productId);
            productsRepository.deleteProductAfterInventory(productId);
        }

        repository.deleteSupplierAfterProducts(supplierId);
    }

}