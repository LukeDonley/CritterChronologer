package com.udacity.jdnd.course3.critter.schedule.data;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.user.data.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleData, Long> {
    List<ScheduleData> findByPets(PetData pet);
    List<ScheduleData> findByEmployees(EmployeeData employee);
    List<ScheduleData> findByPetsIn(List<PetData> pets);
}
