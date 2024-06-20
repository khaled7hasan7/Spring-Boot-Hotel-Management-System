package com.example.test1.service;


import com.example.test1.Model.DTO.BranchDTO;
import com.example.test1.Model.DTO.CustomerDTO;
import com.example.test1.Model.DTO.EmployeeDTO;
import com.example.test1.Model.DTO.PositionDTO;
import com.example.test1.Model.Entity.Branch;
import com.example.test1.Model.Entity.Customer;
import com.example.test1.Model.Entity.Employee;
import com.example.test1.Model.Entity.Position;
import com.example.test1.repository.BranchRepository;
import com.example.test1.repository.CustomerRepository;
import com.example.test1.repository.EmployeeRepository;
import com.example.test1.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private  PositionRepository repoPos;

    @Autowired
    private BranchRepository repobranch;

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employee = repo.findAll();
        return  employee.stream()
                .map(EmployeeDTO::toDTO)
                .collect(Collectors.toList());
    }



    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> employee = repo.findById(id);
        return employee.map(EmployeeDTO::toDTO).orElse(null);
    }



    public  PositionDTO getPositionById(Integer id) {
        Optional<Position> position = repoPos.findById(id);
        return position.map(PositionDTO::toDTO).orElse(null);
    }


    public  BranchDTO getBranchById(Integer id) {
        Optional<Branch> Branch = repobranch.findById(id);
        return Branch.map(BranchDTO::toDTO).orElse(null);
    }





    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        PositionDTO position = new PositionDTO();
        position = getPositionById(employeeDTO.getPositionId()) ;

        Position p = new Position();
        p.setId(position.getId());
        p.setPositionName(position.getPositionName());



        BranchDTO btdo = new BranchDTO();
        btdo = getBranchById(employeeDTO.getBranchId()) ;

        Branch b = new Branch();
        b.setId(btdo.getId());
        b.setDescription(btdo.getDescription());
        b.setName(btdo.getName());
        b.setLocation(btdo.getLocation());







        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhone(employeeDTO.getPhone());
        employee.setProfileCreationDate(LocalDate.now());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setBranch(b);
        employee.setPosition(p);

        Employee savedEmployee = repo.save(employee);
        return EmployeeDTO.toDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = repo.findById(id);

        PositionDTO position = new PositionDTO();
        position = getPositionById(employeeDTO.getPositionId()) ;

        Position p = new Position();
        p.setId(position.getId());
        p.setPositionName(position.getPositionName());




        BranchDTO btdo = new BranchDTO();
        btdo = getBranchById(employeeDTO.getBranchId()) ;

        Branch branch = new Branch();
        branch.setId(btdo.getId());
        branch.setDescription(btdo.getDescription());
        branch.setName(btdo.getName());
        branch.setLocation(btdo.getLocation());




        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setAddress(employeeDTO.getAddress());
            employee.setPhone(employeeDTO.getPhone());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPassword(employeeDTO.getPassword());
            employee.setBranch(branch);
            employee.setPosition(p);
            Employee updatedemployee = repo.save(employee);

            return EmployeeDTO.toDTO(updatedemployee);
        } else {
            return null;
        }
    }

    public boolean deleteEmployee(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
