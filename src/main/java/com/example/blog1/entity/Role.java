package com.example.blog1.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;


// Constructors, getters, and setters
}
