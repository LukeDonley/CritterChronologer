package com.udacity.jdnd.course3.critter.pet.data;

import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class PetData {
    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    private String name;
    @ManyToOne(targetEntity = CustomerData.class, optional = false)
    private CustomerData customer;
    private LocalDate birthDate;
    private String notes;
}
