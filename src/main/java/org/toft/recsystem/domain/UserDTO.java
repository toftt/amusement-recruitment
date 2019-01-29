package org.toft.recsystem.domain;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UserDTO {
    @NotNull
    @NotEmpty
    String firstName;

    @NotNull
    @NotEmpty
    String lastName;

    @NotNull
    @NotEmpty
    String socialSecurityNumber;

    @NotNull
    @NotEmpty
    String email;

    @NotNull
    @NotEmpty
    String password;

    @NotNull
    @NotEmpty
    String username;
}
