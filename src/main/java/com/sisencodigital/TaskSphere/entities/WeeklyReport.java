package com.sisencodigital.TaskSphere.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "weekly_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "project_id")
    private Project project;

   @Column(nullable = false)
    private LocalDate weekStartDate;

   @Column(nullable = false)
    private LocalDate weekEndingDate;

   @Column(nullable = false,columnDefinition = "TEXT")
    private String taskPlanned;

   @Column(nullable = false,columnDefinition = "TEXT")
    private String taskCompleted;

    @PositiveOrZero
    private Double hoursWorked;

    @Column(columnDefinition = "TEXT")
    private String bugs;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ReportStatus reportStatus=ReportStatus.DRAFT;

    private LocalDateTime submittedAt;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
        this.updatedAt=this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }



}
