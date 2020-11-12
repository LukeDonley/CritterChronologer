package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.data.PetData;
import com.udacity.jdnd.course3.critter.pet.data.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.data.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.data.ScheduleData;
import com.udacity.jdnd.course3.critter.schedule.data.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.data.CustomerData;
import com.udacity.jdnd.course3.critter.user.data.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.data.EmployeeData;
import com.udacity.jdnd.course3.critter.user.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        List<PetData> pets = petRepository.findAllById(scheduleDTO.getPetIds());
        List<EmployeeData> employees = employeeRepository.findAllById(scheduleDTO.getEmployeeIds());

        ScheduleData newSchedule = new ScheduleData(
                employees,
                pets,
                scheduleDTO.getDate(),
                scheduleDTO.getActivities()
        );
        newSchedule = scheduleRepository.save(newSchedule);
        scheduleDTO.setId(newSchedule.getId());
        return scheduleDTO;
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleData> schedules = scheduleRepository.findAll();
        return schedules.stream().map(this::createDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        PetData pet = petRepository.getOne(petId);
        List<ScheduleData> schedules = scheduleRepository.findByPets(pet);
        return schedules.stream().map(this::createDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        EmployeeData employee = employeeRepository.getOne(employeeId);
        List<ScheduleData> schedules = scheduleRepository.findByEmployees(employee);
        return schedules.stream().map(this::createDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        CustomerData customer = customerRepository.getOne(customerId);
        List<ScheduleData> schedules = scheduleRepository.findByPetsIn(customer.getPets());
        return schedules.stream().map(this::createDTO).collect(Collectors.toList());
    }

    private ScheduleDTO createDTO(ScheduleData schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setEmployeeIds(
                schedule.getEmployees().stream().map(EmployeeData::getId).collect(Collectors.toList())
        );
        scheduleDTO.setPetIds(
                schedule.getPets().stream().map(PetData::getId).collect(Collectors.toList())
        );
        return scheduleDTO;
    }
}
