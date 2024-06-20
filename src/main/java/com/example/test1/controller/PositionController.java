package com.example.test1.controller;


import com.example.test1.Model.DTO.PositionDTO;
import com.example.test1.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/positions")
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        List<PositionDTO> positions = positionService.getAllCustomers();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable int id) {
        PositionDTO position = positionService.getPositionById(id);
        if (position != null) {
            return ResponseEntity.ok(position);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/positions")
    public ResponseEntity<PositionDTO> createPosition(@RequestBody PositionDTO positionDTO) {
        PositionDTO createdPosition = positionService.createposition(positionDTO);
        return ResponseEntity.ok(createdPosition);
    }

    @PutMapping("/positions/{id}")
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable Integer id, @RequestBody PositionDTO positionDTO) {
        PositionDTO updatedPosition = positionService.updateCustomer(id, positionDTO);
        if (updatedPosition != null) {
            return ResponseEntity.ok(updatedPosition);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Integer id) {
        boolean deleted = positionService.deletePosition(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}