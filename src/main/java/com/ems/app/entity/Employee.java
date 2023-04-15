package com.ems.app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
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
