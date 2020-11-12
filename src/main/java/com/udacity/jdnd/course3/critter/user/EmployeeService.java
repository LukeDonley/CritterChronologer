package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeData newEmployee = new EmployeeData(
                employeeDTO.getName(),
                employeeDTO.getSkills(),
                employeeDTO.getDaysAvailable()
        );
        newEmployee = employeeRepository.save(newEmployee);
        employeeDTO.setId(newEmployee.getId());
        return employeeDTO;
    }

    public EmployeeDTO getEmployeeById(long employeeId) {
        EmployeeData employeeData = employeeRepository.getOne(employeeId);
        return createDTO(employeeData);
    }

    private EmployeeDTO createDTO(EmployeeData employeeData) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeData.getId());
        employeeDTO.setName(employeeData.getName());
        employeeDTO.setDaysAvailable(employeeData.getDaysAvailable());
        employeeDTO.setSkills(employeeData.getSkills());
        return employeeDTO;
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
        EmployeeData employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }

    public List<EmployeeDTO> findAvailableEmployees(EmployeeRequestDTO employeeDTO) {
        LocalDate serviceDate = employeeDTO.getDate();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        List<EmployeeData> employees = employeeRepository
                .findByDaysAvailable(serviceDate.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees.stream().map(this::createDTO).collect(Collectors.toList());
    }
}
