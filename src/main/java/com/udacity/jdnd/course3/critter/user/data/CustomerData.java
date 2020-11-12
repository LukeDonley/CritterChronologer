package com.udacity.jdnd.course3.critter.user.data;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class CustomerData implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = PetData.class)
    private List<PetData> pets;

    public CustomerData(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }
}
