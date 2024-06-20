package com.example.test1.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;


//    @ManyToOne(fetch = FetchType.LAZY)
//
//    @JoinColumn(name = "room_type_id")
//    private RoomType roomType;

    private String customerName;


    private Integer roomNumber;

    private Integer roomTypeId;

    private String roomTypeName;

    private Integer capacity;

    private Double classPrice;

    private Integer floor;

    private String description;
}
