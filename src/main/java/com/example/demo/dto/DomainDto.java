package com.example.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainDto {
    private int id;
    private String name;
}