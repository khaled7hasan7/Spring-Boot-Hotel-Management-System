package com.example.test1.controller;


import com.example.test1.Exception.BookingNotFoundException;
import com.example.test1.Exception.RoomAlreadyBookedException;
import com.example.test1.Model.DTO.BookingDTO;
import com.example.test1.Model.Entity.Receipt;
import com.example.test1.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping
public class BookingController {



    @Autowired
    private BookingService bookingService;



    @GetMapping("/bookings")
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//
//    @PostMapping("/bookings")
//    public ResponseEntity<?> createBooking1(@RequestBody BookingDTO bookingDTO) {
//        try {
//            BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
//            return ResponseEntity.ok(createdBooking);
//        } catch (IllegalArgumentException | IllegalStateException e) {
//            return ResponseEntity.badRequest().body("null");
////
//
//        }
//    }

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
            return ResponseEntity.ok(createdBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (RoomAlreadyBookedException e) {
            return ResponseEntity.badRequest().body("The room is already booked");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Integer id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        if (bookingService.deleteBooking(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<?> checkoutBooking(@PathVariable Integer id) {
        try {
            bookingService.checkoutBooking(id);
           // Receipt receipt = bookingService.checkoutBooking(id);
            return ResponseEntity.ok("Room status updated to empty");
        } catch (BookingNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }


    @GetMapping("/bookingSearch")
    public ResponseEntity<List<BookingDTO>> searchBookings(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) Integer CustomerId,
            @RequestParam(required = false) LocalDate date) {

        List<BookingDTO> bookings = bookingService.searchBookings(customerName,CustomerId, date);
        if (!bookings.isEmpty()) {
            return ResponseEntity.ok(bookings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}