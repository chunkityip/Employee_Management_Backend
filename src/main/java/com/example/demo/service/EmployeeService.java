package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface EmployeeService {
    void createEmployee(EmployeeDto employee);
    void updateEmployee(EmployeeDto employee);
    void deleteEmployee(String email);
    Optional<EmployeeDto> findByEmail(String email);
    List<EmployeeDto> searchByFirstname(String firstname);
    List<EmployeeDto> findByExperience(int experience);
    boolean existsByEmail(String email);
}