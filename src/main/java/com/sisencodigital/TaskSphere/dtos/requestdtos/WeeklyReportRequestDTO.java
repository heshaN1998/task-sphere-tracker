package com.sisencodigital.TaskSphere.dtos.requestdtos;

import com.sisencodigital.TaskSphere.entities.ReportStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class WeeklyReportRequestDTO {
    @NotNull(message = "Week start date required")
    private LocalDate weekStartDate;

    @NotNull(message = "Week ending date required")
    private LocalDate weekEndingDate;

    private Long projectId;

    @NotBlank(message = "Task planned required")
    private String taskPlaned;

    @NotBlank(message = "Task completed is required")
    private String taskCompleted;

    private Double hoursWorked;
    private String bugs;
    private String notes;
    private ReportStatus reportStatus;

}
