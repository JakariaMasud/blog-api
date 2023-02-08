package com.example.blogApi.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    @NotEmpty(message = "name must not be empty")
    @Size(min = 5, max = 20, message = "Name min size is 5 and max size is 20")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotEmpty(message = "about can not be empty")
    private String about;
}
