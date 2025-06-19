package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Employee Management", description = "Endpoints for managing employee information")
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create a new employee" , description = "Create a new employee")
    @PostMapping
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeDto employee) {
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update existing employee" , description = "Update existing employee information")
    @PutMapping
    public ResponseEntity<Void> updateEmployee(@Valid @RequestBody EmployeeDto employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete employee by email" , description = "Delete employee by email")
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description = "Email of employee to delete", required = true)
            @PathVariable String email) {
        employeeService.deleteEmployee(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Find employee by email" , description = "Find employee by exact email match")
    @GetMapping("/{email}")
    public ResponseEntity<EmployeeDto> findByEmail(
            @Parameter(description = "Email of employee to find", required = true)
            @PathVariable String email) {
        return employeeService.findByEmail(email)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Search employees by firstname pattern" , description = "Search employees by firstname pattern")
    @GetMapping("/search/firstname/{firstname}")
    public ResponseEntity<List<EmployeeDto>> searchByFirstname(
            @Parameter(description = "Firstname pattern to search", required = true)
            @PathVariable String firstname) {
        List<EmployeeDto> employees = employeeService.searchByFirstname(firstname);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(summary = "Find employees by experience" , description = "Find employees by years of experience")
    @GetMapping("/search/experience/{experience}")
    public ResponseEntity<List<EmployeeDto>> findByExperience(
            @Parameter(description = "Years of experience to search", required = true)
            @PathVariable int experience) {
        List<EmployeeDto> employees = employeeService.findByExperience(experience);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(summary = "Check if email exists" , description = "Check if email exists")
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> existsByEmail(
            @Parameter(description = "Email to check", required = true)
            @PathVariable String email) {
        return new ResponseEntity<>(employeeService.existsByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeDto> findAllEmployees() {
        return employeeService.getAllEmployees();
    }
}