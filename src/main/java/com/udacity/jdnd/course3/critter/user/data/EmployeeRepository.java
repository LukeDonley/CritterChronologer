package com.udacity.jdnd.course3.critter.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeData, Long> {
    List<EmployeeData> findByDaysAvailable(DayOfWeek dayOfWeek);
}
