package com.example.test1.controller;


import com.example.test1.Model.DTO.ReceiptDTO;
import com.example.test1.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/receipts")
    public ResponseEntity<List<ReceiptDTO>> getAllReceipts() {
        List<ReceiptDTO> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/receipts/{id}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable int id) {
        ReceiptDTO receipt = receiptService.getReceiptById(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/receipts")
    public ResponseEntity<ReceiptDTO> createReceipt(@RequestBody ReceiptDTO receiptDTO) {
        ReceiptDTO createdReceipt = receiptService.createReceipt(receiptDTO);
        return ResponseEntity.ok(createdReceipt);
    }

    @PutMapping("/receipts/{id}")
    public ResponseEntity<ReceiptDTO> updateReceipt(@PathVariable Integer id, @RequestBody ReceiptDTO receiptDTO) {
        ReceiptDTO updatedReceipt = receiptService.updateReceipt(id, receiptDTO);
        if (updatedReceipt != null) {
            return ResponseEntity.ok(updatedReceipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//
//    @DeleteMapping("/receipts/{id}")
//    public ResponseEntity<Void> deleteReceipt(@PathVariable Integer id) {
//        boolean deleted = receiptService.deleteReceipt(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}