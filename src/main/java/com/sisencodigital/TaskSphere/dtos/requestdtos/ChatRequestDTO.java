package com.sisencodigital.TaskSphere.dtos.requestdtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChatRequestDTO {
    @NotBlank(message = "Message cannot be empty")
    private String message;

}
