package org.toft.recsystem.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonDTO {
    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    String surname;

    @NotNull
    @NotEmpty
    String ssn;

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
