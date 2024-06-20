package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.Status;
import org.antlr.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {


    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 2, max = 255)
    private String statusName;


    public static StatusDTO toDTO(Status status) {
        StatusDTO dto = new StatusDTO();
        dto.setId(status.getId());
        dto.setStatusName(status.getStatusName());
        return dto;

    }





}
