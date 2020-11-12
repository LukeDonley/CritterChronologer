package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.data.PetDTO;
import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.pet.data.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import com.udacity.jdnd.course3.critter.user.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetDTO createPet(PetDTO petDTO) {
        CustomerData customer = customerRepository.getOne(petDTO.getOwnerId());
        PetData newPet = new PetData(
                petDTO.getType(),
                petDTO.getName(),
                customer,
                petDTO.getBirthDate(),
                petDTO.getNotes()
        );
        newPet = petRepository.save(newPet);

        List<PetData> customerPets = customer.getPets();
        if (customerPets == null) {
            customerPets = new ArrayList<PetData>();
        }
        customerPets.add(newPet);
        customer.setPets(customerPets);
        customerRepository.save(customer);
        petDTO.setId(newPet.getId());
        return petDTO;
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

    public PetDTO getPet(long petId) {
        return createDTO(petRepository.getOne(petId));
    }

    public List<PetDTO> getAllPets() {
        List<PetData> pets = petRepository.findAll();
        return pets.stream().map(this::createDTO).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<PetData> pets = customerRepository.getOne(ownerId).getPets();
        return pets.stream().map(this::createDTO).collect(Collectors.toList());
    }
}
