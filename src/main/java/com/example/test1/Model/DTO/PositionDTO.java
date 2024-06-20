package com.example.test1.Model.DTO;
import com.example.test1.Model.Entity.Position;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {


    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 2, max = 255)
    private String positionName;


    public static PositionDTO toDTO(Position position) {
        PositionDTO dto = new PositionDTO();
        dto.setId(position.getId());
        dto.setPositionName(position.getPositionName());
        return dto;
    }

}
