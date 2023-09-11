package com.treeleaf.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;
}
