package com.example.test1.repository;

import com.example.test1.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test1.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
    Customer findByEmailOrPassword(String email, String password);

    // Query to find customers by name or ID
    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "c.id = :customerId")
    List<Customer> findByFirstNameOrLastNameOrId(
            @Param("query") String query,
            @Param("customerId") Integer customerId);


}