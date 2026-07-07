package com.sisencodigital.TaskSphere.dtos.requestdtos;

import com.sisencodigital.TaskSphere.entities.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "full name required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "include valued email")
    private String  email;

    @NotBlank
    @Size(min = 6,message = "password must be over 5 characters")
    private String password;

    private Role role;
}
