package com.example.demo.user;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roles;
}
