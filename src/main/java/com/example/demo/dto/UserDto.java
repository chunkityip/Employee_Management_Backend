package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto {
    @NotBlank
    @Size(max = 100)
    private String userName;

    @NotBlank
    @Size(max = 100)
    private String password;
}
