package com.example.test1.Model.DTO;


import com.example.test1.Model.Entity.Branch;
import com.example.test1.Model.Entity.Employee;
import com.example.test1.Model.Entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    @NotNull
    private Integer id;

   @NotNull
   @Size(min = 2, max = 50)
    private String name;



    @NotNull
    @Size(min = 10, max = 15)
    private String phone;

    @NotNull
    @Size(min = 5, max = 150)
    private String location;



    @Size(min = 5, max = 500)
    private String description;



    public static BranchDTO toDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(branch.getId());
        branchDTO.setName(branch.getName());
        branchDTO.setPhone(branch.getPhone());
        branchDTO.setLocation(branch.getLocation());
        branchDTO.setDescription(branch.getDescription());
        return branchDTO;
    }


}
