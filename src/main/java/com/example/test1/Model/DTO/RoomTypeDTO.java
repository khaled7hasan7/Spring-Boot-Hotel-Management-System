package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.RoomType;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO {


    @NotNull
    private Integer id;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double classPrice;

    @NotNull
    @Size(min = 1, max = 255)
    private String className;

    @NotNull
    @Min(1)
    private Integer capacity;

    private String description;


    public static RoomTypeDTO fromEntity(RoomType entity) {
        RoomTypeDTO dto = new RoomTypeDTO();

        dto.setId(entity.getId());
        dto.setClassPrice(entity.getClassPrice());
        dto.setClassName(entity.getClassName());
        dto.setCapacity(entity.getCapacity());
        dto.setDescription(entity.getDescription());
        return dto;
    }

}
