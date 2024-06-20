package com.example.test1.controller;

import com.example.test1.Model.DTO.CustomerDTO;
import com.example.test1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping("/customers")

    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = service.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        CustomerDTO customer = service.getCustomerById(id);
       return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = service.createCustomer(customerDTO);
        return ResponseEntity.status(201).body(createdCustomer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = service.updateCustomer(id, customerDTO);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        boolean deleted = service.deleteCustomer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }





//    @GetMapping("/search")
//    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam(required = false) String query) {
//        List<CustomerDTO> customers = service.searchCustomers(query);
//        if (!customers.isEmpty()) {
//            return ResponseEntity.ok(customers);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



}
