package com.sisencodigital.TaskSphere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "weekly_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyReport {
    private Long id;
    private  User user;
    private Project project;
    private LocalDate weesStartDate;
    private LocalDate weekEndingDate;
    private String taskPlanned;
    private String taskCompleted;
    private Double hoursWorked;
    private String bugs;
    private String notes;
    private ReportStatus reportStatus;
    private LocalDateTime localDateTime;


}
