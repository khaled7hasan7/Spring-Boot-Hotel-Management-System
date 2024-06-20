package com.example.test1.Model.DTO;

import com.example.test1.Model.Entity.Branch;
import com.example.test1.Model.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;

    @NotNull
    @Size(min = 5, max = 255)
    private String address;

    @NotNull
    @Size(min = 10, max = 15)
    private String phone;

    @NotNull
    private LocalDate profileCreation;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    @NotNull
    private Integer positionId;


    @NotNull
    private Integer branchId;


    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPassword(employee.getPassword());
        employeeDTO.setPositionId(employee.getPosition().getId());
        employeeDTO.setProfileCreation(employee.getProfileCreationDate());

        employeeDTO.setBranchId(employee.getBranch().getId());
        return employeeDTO;
    }



}
