package com.udacity.jdnd.course3.critter.user.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class EmployeeData implements Serializable {
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

    public EmployeeData(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }
}
