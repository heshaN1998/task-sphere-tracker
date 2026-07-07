package com.sisencodigital.TaskSphere.dtos.requestdtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectRequestDTO {
    @NotBlank
    private String name;

    private String description;
}
