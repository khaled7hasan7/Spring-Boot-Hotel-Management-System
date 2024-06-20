package com.example.test1.service;


import com.example.test1.Exception.BookingNotFoundException;
import com.example.test1.Exception.RoomAlreadyBookedException;
import com.example.test1.Model.DTO.BookingDTO;
import com.example.test1.Model.DTO.ReceiptDTO;
import com.example.test1.Model.Entity.Booking;
import com.example.test1.Model.Entity.Receipt;
import com.example.test1.Model.Entity.Room;
import com.example.test1.Model.Entity.Status;
import com.example.test1.repository.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ReceiptRepository repositoryRec;



    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(BookingDTO::toDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Integer id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(BookingDTO::toDTO).orElse(null);
    }


//
//    public BookingDTO createBooking2(BookingDTO bookingDTO) {
//        Optional<Room> optionalRoom = roomRepository.findById(bookingDTO.getRoomid());
//        if (!optionalRoom.isPresent()) {
//            throw new IllegalArgumentException("Invalid Room ID");
//        }
//
//        Room room = optionalRoom.get();
//
//        Status bookedStatus = statusRepository.findByStatusName("booked")
//                .orElseThrow(() -> new IllegalStateException("Status 'booked' not found"));
//
//        if (room.getStatus().equals(bookedStatus)) {
//            throw new IllegalStateException("Room is already booked");
//        }
//
//        // Create the booking
//        Booking booking = new Booking();
//        booking.setCustomer(customerRepository.findById(bookingDTO.getCustomerid())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID")));
//        booking.setBranch(branchRepository.findById(bookingDTO.getBranchid())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID")));
//        booking.setRoom(room);
//        booking.setCheckin(bookingDTO.getCheckin());
//        booking.setCheckout(bookingDTO.getCheckout());
//        booking.setBookingdate(LocalDate.now());
//
//        Booking savedBooking = bookingRepository.save(booking);
//
//        // Update the room status to booked
//        room.setStatus(bookedStatus);
//        roomRepository.save(room);
//
//        return BookingDTO.toDTO(savedBooking);
//    }




    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Optional<Room> optionalRoom = roomRepository.findById(bookingDTO.getRoomid());
        if (!optionalRoom.isPresent()) {
            throw new IllegalArgumentException("Invalid Room ID");
        }

        Room room = optionalRoom.get();

        Status bookedStatus = statusRepository.findByStatusName("booked")
                .orElseThrow(() -> new IllegalStateException("Status 'booked' not found"));

        if (room.getStatus().equals(bookedStatus)) {
            throw new RoomAlreadyBookedException("The room is already booked");
        }

        // Create the booking
        Booking booking = new Booking();
        booking.setCustomer(customerRepository.findById(bookingDTO.getCustomerid())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID")));
        booking.setBranch(branchRepository.findById(bookingDTO.getBranchid())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID")));
        booking.setRoom(room);
        booking.setCheckin(bookingDTO.getCheckin());
        booking.setCheckout(bookingDTO.getCheckout());
        booking.setBookingdate(LocalDate.now());
        booking.setStatus("Effective");
        Booking savedBooking = bookingRepository.save(booking);

        // Update the room status to booked
        room.setStatus(bookedStatus);
        roomRepository.save(room);

        return BookingDTO.toDTO(savedBooking);
    }





    public BookingDTO updateBooking(Integer id, BookingDTO bookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setCustomer(customerRepository.findById(bookingDTO.getCustomerid())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID")));
            booking.setBranch(branchRepository.findById(bookingDTO.getBranchid())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID")));
            booking.setRoom(roomRepository.findById(bookingDTO.getRoomid())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Room ID")));
            booking.setCheckin(bookingDTO.getCheckin());
            booking.setCheckout(bookingDTO.getCheckout());
            booking.setBookingdate(bookingDTO.getBookingdate());

            Booking updatedBooking = bookingRepository.save(booking);
            return BookingDTO.toDTO(updatedBooking);
        } else {
            return null;
        }
    }



    @Transactional
    public Receipt checkoutBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        booking.setStatus("Inactive");

        Room room = booking.getRoom();

        Status emptyStatus = statusRepository.findByStatusName("empty")
                .orElseThrow(() -> new IllegalStateException("Status 'empty' not found"));


        room.setStatus(emptyStatus);
        roomRepository.save(room);
        // bookingRepository.delete(booking);

        try{

            Receipt receipt = new Receipt();
            receipt.setBooking(booking);
            receipt.setCustomerName(booking.getCustomer().getFirstName()+" "+booking.getCustomer().getLastName());
            receipt.setRoomNumber(room.getRoomNumber());
            receipt.setRoomTypeId(room.getRoomType().getId());
            receipt.setRoomTypeName(room.getRoomType().getClassName());
            receipt.setCapacity(room.getRoomType().getCapacity());

            LocalDate Checkin = booking.getCheckin() ;
            LocalDate Checkout = LocalDate.now() ;
            long daysBetween = ChronoUnit.DAYS.between(Checkin, Checkout);

            receipt.setClassPrice(room.getRoomType().getClassPrice()*daysBetween);
            receipt.setFloor(room.getFloor().getFloorNumber());
            receipt.setDescription("Receipt for booking ID: " + bookingId +"        Date"+LocalDate.now());

            repositoryRec.save(receipt);

                return receipt;

        }catch (Exception e){


            return null;
        }





    }




    public boolean deleteBooking(Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }









    public List<BookingDTO> searchBookings(String customerName,Integer customerID, LocalDate date) {
        List<Booking> bookings;
        if (customerName != null && !customerName.isEmpty() && date != null) {
            bookings = bookingRepository.findByCustomerNameOrCustomerIdAndBookingDate(customerName,customerID ,date);
        } else if (customerName != null && !customerName.isEmpty()) {
            bookings = bookingRepository.findByCustomerNameOrCustomerIdAndBookingDate(customerName,customerID ,date);
        } else if (date != null) {
            bookings = bookingRepository.findByBookingdate(date);
        } else {
            bookings = bookingRepository.findAll();
        }
        return bookings.stream()
                .map(BookingDTO::toDTO)
                .collect(Collectors.toList());
    }









}