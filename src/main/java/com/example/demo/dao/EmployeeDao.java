package com.example.demo.dao;

import com.example.demo.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    boolean existsByEmail(String email);

    Optional<EmployeeDto> findByEmail(String email);

    List<EmployeeDto> findByFirstnameStartingWith(String firstname);

    List<EmployeeDto> findByExperience(int experience);

    List<EmployeeDto> findByDomain(String domain);

    void insertEmployee(EmployeeDto employee);

    void updateEmployee(EmployeeDto employee);

    void deleteByEmail(String email);

    List<EmployeeDto> findAllEmployees();
}