package com.tuneit.example.model;


import lombok.Data;

@Data
public class Employee {
    private String firstName;
    private String lastName;
    private Integer age;
    private boolean canEdit;
}
