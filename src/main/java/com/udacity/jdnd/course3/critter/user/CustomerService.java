package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.user.data.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import com.udacity.jdnd.course3.critter.user.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerData saveCustomer(CustomerDTO customer) {
        CustomerData newCustomer = new CustomerData();
        newCustomer.setName(customer.getName());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(newCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerData> customers = customerRepository.findAll();
        return customers.stream().map(this::createDTO).collect(Collectors.toList());
    }

    private CustomerDTO createDTO(CustomerData customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        List<PetData> customerPets = customer.getPets();
        List<Long> petIds = new ArrayList<>();
        if (customerPets != null) {
            petIds = customerPets.stream().map(PetData::getId).collect(Collectors.toList());
        }
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }
}
