package com.udacity.jdnd.course3.critter.user.data;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class CustomerData {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = PetData.class)
    private List<PetData> pets;

    public void addPet(PetData pet) {
        pets.add(pet);
    }

}
