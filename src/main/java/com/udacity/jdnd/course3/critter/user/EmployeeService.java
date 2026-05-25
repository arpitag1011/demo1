package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId){
        return employeeRepository.getOne(employeeId);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDays(days);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> employees = employeeRepository.getAllByDaysContains(date.getDayOfWeek()).stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());
        return employees;
    }
}
