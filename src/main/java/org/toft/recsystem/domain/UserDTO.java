package org.toft.recsystem.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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
