package com.example.test1.service;



import com.example.test1.Model.DTO.CustomerDTO;
import com.example.test1.Model.Entity.Customer;
import com.example.test1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService  {

    @Autowired
    private CustomerRepository repo;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = repo.findAll();
        return customers.stream()
                .map(CustomerDTO::toDTO)
                .collect(Collectors.toList());
    }



    public CustomerDTO getCustomerById(Integer id) {
        Optional<Customer> customer = repo.findById(id);
        return customer.map(CustomerDTO::toDTO).orElse(null);
    }




    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setProfileCreation(LocalDate.now());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());

        Customer savedCustomer = repo.save(customer);
        return CustomerDTO.toDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = repo.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setAddress(customerDTO.getAddress());
            customer.setPhone(customerDTO.getPhone());
            customer.setEmail(customerDTO.getEmail());
            customer.setPassword(customerDTO.getPassword());
            Customer updatedCustomer = repo.save(customer);
            return CustomerDTO.toDTO(updatedCustomer);
        } else {
            return null;
        }
    }


    public boolean deleteCustomer(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}