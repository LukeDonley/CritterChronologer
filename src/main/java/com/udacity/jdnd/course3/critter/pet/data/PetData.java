package com.udacity.jdnd.course3.critter.pet.data;

import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
@Table
@Data
@NoArgsConstructor
public class PetData {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private PetType type;
    private String name;
    @ManyToOne(targetEntity = CustomerData.class, optional = false)
    private CustomerData customer;
    private LocalDate birthDate;
    private String notes;

    public PetData(PetType type, String name, CustomerData customer, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }
}
