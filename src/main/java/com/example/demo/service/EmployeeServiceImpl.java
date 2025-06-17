package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.DuplicateEmailException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void createEmployee(EmployeeDto employee) {
        validateEmployee(employee);
        if (employeeDao.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException(employee.getEmail());
        }
        employee.setPassword(employee.getPassword());
        employeeDao.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(EmployeeDto employee) {
        validateEmployee(employee);
        if (!employeeDao.existsByEmail(employee.getEmail())) {
            throw new EntityNotFoundException("Employee not found with email: " + employee.getEmail());
        }
        if (employee.getPassword() != null) {
            employee.setPassword(employee.getPassword());
        }
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String email) {
        if (!employeeDao.existsByEmail(email)) {
            throw new EntityNotFoundException("Employee not found with email: " + email);
        }
        employeeDao.deleteByEmail(email);
    }

    @Override
    public Optional<EmployeeDto> findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public List<EmployeeDto> findByExperience(int experience) {
        return employeeDao.findByExperience(experience);
    }

    @Override
    public List<EmployeeDto> searchByFirstname(String firstname) {
        return employeeDao.findByFirstnameStartingWith(firstname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeDao.existsByEmail(email);
    }

    private void validateEmployee(EmployeeDto employee) {
        if (employee.getFirstname() == null || employee.getFirstname().trim().isEmpty()) {
            throw new ValidationException("Firstname cannot be empty");
        }
        if (employee.getLastname() == null || employee.getLastname().trim().isEmpty()) {
            throw new ValidationException("Lastname cannot be empty");
        }
        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Invalid email format");
        }
        if (employee.getPassword() == null || employee.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
    }
}
