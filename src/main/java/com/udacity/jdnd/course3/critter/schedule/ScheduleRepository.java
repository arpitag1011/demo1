package com.udacity.jdnd.course3.critter.schedule;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p = :pet")
    List<Schedule> getAllByPetsContains(@Param("pet") Pet pet);

    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e = :employees")
    List<Schedule> getAllByEmployeesContains(@Param("employee") Employee employee);

    List<Schedule> getAllByPetsIn(List<Pet> pets);
}
