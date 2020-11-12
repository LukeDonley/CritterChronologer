package com.udacity.jdnd.course3.critter.schedule.data;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.user.data.EmployeeData;
import com.udacity.jdnd.course3.critter.user.data.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class ScheduleData {
    @Id
    @GeneratedValue
    private long id;
    @ManyToMany(targetEntity = EmployeeData.class)
    private List<EmployeeData> employees;
    @ManyToMany(targetEntity = PetData.class)
    private List<PetData> pets;
    private LocalDate date;
    @ElementCollection
    @CollectionTable(name = "schedule_activities", joinColumns = @JoinColumn(name = "schedule_id"))
    private Set<EmployeeSkill> activities;

    public ScheduleData(List<EmployeeData> employees, List<PetData> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }
}
