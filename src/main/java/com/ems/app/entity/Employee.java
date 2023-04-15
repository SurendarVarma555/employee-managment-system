package com.ems.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
}
