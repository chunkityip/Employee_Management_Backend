package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int id;

    @NotBlank
    @Size(max = 100)
    private String firstname;

    @NotBlank
    @Size(max = 100)
    private String lastname;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    private LocalDate dob;

    @NotNull
    private Long phone;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Min(0)
    private int experience;

    @Size(max = 100)
    private String domain;
}
