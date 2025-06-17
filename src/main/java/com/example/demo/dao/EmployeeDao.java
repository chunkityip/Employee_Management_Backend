package com.example.demo.dao;

import com.example.demo.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    boolean existsByEmail(String email);

    Optional<EmployeeDto> findByEmail(String email);  // Change return type

    //Changing List to Optional
    List<EmployeeDto> findByFirstnameStartingWith(String firstname);

    List<EmployeeDto> findByExperience(int experience);

    void insertEmployee(EmployeeDto employee);

    void updateEmployee(EmployeeDto employee);

    void deleteByEmail(String email);

}
