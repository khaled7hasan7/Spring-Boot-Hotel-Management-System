package com.example.test1.service;

import com.example.test1.Model.DTO.ReceiptDTO;
import com.example.test1.Model.Entity.Receipt;
import com.example.test1.repository.BookingRepository;
import com.example.test1.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;






@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<ReceiptDTO> getAllReceipts() {
        List<Receipt> receipts = receiptRepository.findAll();
        return receipts.stream()
                .map(ReceiptDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ReceiptDTO getReceiptById(int id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return receipt.map(ReceiptDTO::toDTO).orElse(null);
    }

    public ReceiptDTO createReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt();
        receipt.setId(receiptDTO.getId());
        receipt.setBooking(bookingRepository.findById(receiptDTO.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Booking ID")));
        receipt.setDescription(receiptDTO.getDescription());

        Receipt savedReceipt = receiptRepository.save(receipt);
        return ReceiptDTO.toDTO(savedReceipt);
    }

    public ReceiptDTO updateReceipt(Integer id, ReceiptDTO receiptDTO) {
        Optional<Receipt> optionalReceipt = receiptRepository.findById(id);

        if (optionalReceipt.isPresent()) {
            Receipt receipt = optionalReceipt.get();
            receipt.setId(receiptDTO.getId());
            receipt.setBooking(bookingRepository.findById(receiptDTO.getBookingId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Booking ID")));
            receipt.setDescription(receiptDTO.getDescription());

            Receipt updatedReceipt = receiptRepository.save(receipt);
            return ReceiptDTO.toDTO(updatedReceipt);
        } else {
            return null;
        }
    }

    public boolean deleteReceipt(Integer id) {
        if (receiptRepository.existsById(id)) {
            receiptRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}