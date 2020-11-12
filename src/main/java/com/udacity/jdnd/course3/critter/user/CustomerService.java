package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.pet.data.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import com.udacity.jdnd.course3.critter.user.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerData newCustomer = new CustomerData(
                customerDTO.getName(),
                customerDTO.getPhoneNumber(),
                customerDTO.getNotes()
        );
        newCustomer = customerRepository.save(newCustomer);
        customerDTO.setId(newCustomer.getId());
        return customerDTO;
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

    public CustomerDTO getOwnerByPet(long petId) {
        Optional<PetData> pet = petRepository.findById(petId);
        if (pet.get() != null) {
            return createDTO(pet.get().getCustomer());
        } else {
            return null;
        }
    }
}
