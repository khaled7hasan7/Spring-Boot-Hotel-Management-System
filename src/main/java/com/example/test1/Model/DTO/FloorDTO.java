package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.Floor;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO {

    @NotNull
    private Integer id;

    @NotNull
    private Integer floorNumber;


    public static FloorDTO toDTO(Floor floor) {

       FloorDTO dto = new FloorDTO();
       dto.setId(floor.getId());
       dto.setFloorNumber(floor.getFloorNumber());
       return dto;


    }


}
