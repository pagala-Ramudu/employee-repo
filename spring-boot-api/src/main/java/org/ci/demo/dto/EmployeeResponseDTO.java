package org.ci.demo.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String department;
    private double salary;

    // Getters and Setters
}
