package com.sisencodigital.TaskSphere.dtos.responcedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitionStatusResponseDTO {
    private  Long userId;
    private String userFullName;
    private String reportStatus;
    private Long reportId;

}
