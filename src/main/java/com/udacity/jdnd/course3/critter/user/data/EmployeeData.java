package com.udacity.jdnd.course3.critter.user.data;

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Data
@Entity
@Table(name = "employees")
public class EmployeeData {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> skills;
    @ElementCollection
    @CollectionTable(name = "employee_days_available", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<DayOfWeek> daysAvailable;

    public EmployeeData(long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }
}
