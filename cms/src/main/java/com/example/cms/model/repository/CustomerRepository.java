package com.example.cms.model.repository;

import com.example.cms.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // For the active customers view
    List<Customer> findByActiveTrue();

    // For looking up a customer during login/checkout
    Optional<Customer> findByEmail(String email);

    // For targeted marketing or analytics
    List<Customer> findByPreferredNailSizeIgnoreCase(String nailSize);
    List<Customer> findByCityIgnoreCase(String city);
}