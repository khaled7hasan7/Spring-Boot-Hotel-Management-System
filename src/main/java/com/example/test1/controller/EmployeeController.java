package com.example.test1.controller;


import com.example.test1.Model.DTO.CustomerDTO;
import com.example.test1.Model.DTO.EmployeeDTO;

import com.example.test1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllCustomers() {
        List<EmployeeDTO> employee = service.getAllEmployees();
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        EmployeeDTO employee = service.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Validated @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = service.createEmployee(employeeDTO);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @Validated @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = service.updateEmployee(id, employeeDTO);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        boolean deleted = service.deleteEmployee(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
