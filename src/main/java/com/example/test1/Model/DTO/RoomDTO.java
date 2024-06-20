package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.Branch;
import com.example.test1.Model.Entity.Room;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomDTO {


    @NotNull
    private Integer id;

    @NotNull
    private Integer floorId;

    @NotNull
    private Integer roomNumber;

    @NotNull
    private Integer statusId;


    private Integer roomTypeId;

    @NotNull
    private Integer branch;


    public static RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setFloorId(room.getFloor().getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setStatusId(room.getStatus().getId());
        dto.setRoomTypeId(room.getRoomType().getId());
        dto.setBranch(room.getBranch().getId());
        return dto;
    }

}
