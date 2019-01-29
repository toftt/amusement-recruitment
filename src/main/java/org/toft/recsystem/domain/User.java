package org.toft.recsystem.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String email;
    private String password;
    private String username;

}
