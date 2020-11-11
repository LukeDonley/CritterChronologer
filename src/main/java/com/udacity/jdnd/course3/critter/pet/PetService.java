package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.data.PetDTO;
import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.pet.data.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import com.udacity.jdnd.course3.critter.user.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetData savePet(PetData pet, Long customerId) {
        CustomerData customer = customerRepository.getOne(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.addPet(pet);
        customerRepository.save(customer);
        return pet;
    }

    public List<PetData> getPetsForCustomer(Long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    private PetDTO createDTO(PetData pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        return petDTO;
    }
}
