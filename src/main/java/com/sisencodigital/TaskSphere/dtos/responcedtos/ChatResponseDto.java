package com.sisencodigital.TaskSphere.dtos.responcedtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatResponseDto {
    private String answer;
    private String timestamp;
}
