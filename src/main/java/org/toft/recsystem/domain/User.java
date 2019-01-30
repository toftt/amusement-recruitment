package org.toft.recsystem.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 32)
    private String username;

    @Column(unique = true, length = 100)
    private String email;

    @ManyToOne
    private Role role;

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String password;

}
