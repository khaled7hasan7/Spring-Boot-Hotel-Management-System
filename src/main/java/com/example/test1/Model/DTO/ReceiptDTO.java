package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.Receipt;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {


    @NotNull
    private Integer id;

    @NotNull
    private Integer bookingId;



    private Integer roomNumber;

    private Integer roomTypeId;

    private String roomTypeName;

    private Integer capacity;

    private Double classPrice;

    private Integer floor;

    private String description;

    public static ReceiptDTO toDTO(Receipt receipt) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setId(receipt.getId());
        receiptDTO.setBookingId(receipt.getBooking().getId());
        receiptDTO.setRoomNumber(receipt.getRoomNumber());
        receiptDTO.setRoomTypeId(receipt.getRoomTypeId());
        receiptDTO.setRoomTypeName(receipt.getRoomTypeName());
        receiptDTO.setCapacity(receipt.getCapacity() );
        receiptDTO.setClassPrice(receipt .getClassPrice());
        receiptDTO.setFloor(receipt.getFloor());
        receiptDTO.setDescription(receipt.getDescription());
        return receiptDTO;
    }


}
