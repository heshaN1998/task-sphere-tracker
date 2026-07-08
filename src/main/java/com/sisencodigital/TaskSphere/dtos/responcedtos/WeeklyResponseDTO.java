package com.sisencodigital.TaskSphere.dtos.responcedtos;

import com.sisencodigital.TaskSphere.entities.ReportStatus;
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
    private String userFullName;

    private Long projectId;
    private String projectName;

    private LocalDate weekStartDate;
    private LocalDate weekEndingDate;
    private String taskPlanned;
    private String taskCompleted;
    private Double hoursWorked;

    private String bugs;
    private String notes;

    private ReportStatus reportStatus;
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
