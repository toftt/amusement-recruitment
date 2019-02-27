package org.toft.recsystem.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class WebsiteUserDto {
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String lastName;
    @Size(min = 10, max = 12)
    private String socialSecurityNumber;
    @Email
    @Size(max = 1000)
    private String email;
    @Size(min = 6, max = 28)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 1024)
    private String password;
}
