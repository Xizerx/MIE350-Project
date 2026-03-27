package com.example.cms.controller;

import com.example.cms.model.entity.Customer;
import com.example.cms.model.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    List<Customer> retrieveAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/active")
    List<Customer> retrieveActiveCustomers() {
        return repository.findByActiveTrue();
    }

    @GetMapping("/customers/{id}")
    Customer retrieveCustomer(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @GetMapping("/customers/email/{email}")
    Customer retrieveCustomerByEmail(@PathVariable("email") String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @GetMapping("/customers/nail-size/{size}")
    List<Customer> retrieveCustomersByNailSize(@PathVariable("size") String size) {
        return repository.findByPreferredNailSizeIgnoreCase(size);
    }

    @GetMapping("/customers/city/{city}")
    List<Customer> retrieveCustomersByCity(@PathVariable("city") String city) {
        return repository.findByCityIgnoreCase(city);
    }

    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer updatedCustomer) {
        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhone(updatedCustomer.getPhone());
                    customer.setAddress(updatedCustomer.getAddress());
                    customer.setCity(updatedCustomer.getCity());
                    customer.setProvince(updatedCustomer.getProvince());
                    customer.setPostalCode(updatedCustomer.getPostalCode());
                    return repository.save(customer);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // SPECIALIZED FEATURE: Update Size Profile Only
    @PutMapping("/customers/{id}/size-profile")
    Customer updateSizeProfile(@PathVariable("id") Long id, @RequestBody Map<String, String> sizeProfile) {
        return repository.findById(id)
                .map(customer -> {
                    if (sizeProfile.containsKey("nailSize")) {
                        customer.setPreferredNailSize(sizeProfile.get("nailSize"));
                    }
                    if (sizeProfile.containsKey("necklaceLength")) {
                        customer.setPreferredNecklaceLength(sizeProfile.get("necklaceLength"));
                    }
                    if (sizeProfile.containsKey("sunglassesSize")) {
                        customer.setPreferredSunglassesSize(sizeProfile.get("sunglassesSize"));
                    }
                    if (sizeProfile.containsKey("style")) {
                        customer.setPreferredStyle(sizeProfile.get("style"));
                    }
                    return repository.save(customer);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // Soft-delete mechanism
    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") Long id) {
        repository.findById(id).ifPresent(customer -> {
            customer.setActive(false); // Soft delete instead of dropping the row
            repository.save(customer);
        });
    }
}