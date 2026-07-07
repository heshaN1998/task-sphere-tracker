package com.sisencodigital.TaskSphere.dtos.responcedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyResponseDTO {
    private Long id;
    private Long userId;

    private Long projectId;
    private String projectName;

    private String weekStartDate;
    private String weekEndingDate;
    private String taskPlanned;
    private String taskCompleted;
    private Double hourseWorked;

    private String bugs;
    private String nots;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
