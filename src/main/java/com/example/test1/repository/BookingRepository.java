package com.example.test1.repository;


import com.example.test1.Model.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test1.Model.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


    @Query("SELECT b FROM Booking b WHERE " +
            "(LOWER(b.customer.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(b.customer.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "b.customer.id = :customerId) " +
            "AND b.bookingdate = :bookingDate AND b.status='Effective'")
    List<Booking> findByCustomerNameOrCustomerIdAndBookingDate(
            @Param("query") String query,
            @Param("customerId") Integer customerId,
            @Param("bookingDate") LocalDate bookingDate);


    List<Booking> findByBookingdate(LocalDate bookingDate);


}