package com.example.test1.Model.DTO;


import com.example.test1.Model.Entity.Booking;
import com.example.test1.Model.Entity.Branch;
import com.example.test1.Model.Entity.Customer;
import com.example.test1.Model.Entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {


    @NotNull
    @Size(min = 0, max = 250)
    private Integer id;


    @NotNull
    private Integer customerid;



    @NotNull
    @Size(min = 0, max = 250)
    private Integer branchid;


    @NotNull
    private LocalDate checkout;

    @NotNull
    @Size(min = 0, max = 250)
    private LocalDate checkin;


    @Column(nullable = false)
    private String status;

    @ManyToOne
    private Integer roomid;

    @NotNull
    @Size(min = 0, max = 250)
    private LocalDate bookingdate;



    public static BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerid(booking.getCustomer().getId());
        dto.setRoomid(booking.getRoom().getId());
        dto.setBranchid(booking.getBranch().getId());
        dto.setCheckout(booking.getCheckout());
        dto.setCheckin(booking.getCheckin());
        dto.setBookingdate(booking.getBookingdate());
        dto.setStatus(booking.getStatus());
        return dto;

    }

    public BookingDTO(Integer id, Integer customerid, Integer branchid, LocalDate checkout, LocalDate checkin, Integer roomid) {
        this.id = id;
        this.customerid = customerid;
        this.branchid = branchid;
        this.checkout = checkout;
        this.checkin = checkin;
        this.roomid = roomid;
    }
}
