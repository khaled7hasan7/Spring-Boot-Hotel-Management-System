package com.example.test1.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(nullable = false)
    private LocalDate checkout;

    @Column(nullable = false)
    private LocalDate checkin;


    @Column(nullable = false)
    private String status;

    private LocalDate bookingdate;

    public Booking(Integer id, Customer customer, Room room, Branch branch, LocalDate checkout, LocalDate checkin) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.branch = branch;
        this.checkout = checkout;
        this.checkin = checkin;
    }
}