package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN e.days d WHERE d = :dayOfWeek")
    List<Employee> getAllByDaysContains(@Param("dayOfWeek") DayOfWeek dayOfWeek);
}
