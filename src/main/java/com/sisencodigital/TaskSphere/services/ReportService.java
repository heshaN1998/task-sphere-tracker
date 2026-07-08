package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.requestdtos.WeeklyReportRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.WeeklyResponseDTO;
import com.sisencodigital.TaskSphere.entities.Project;
import com.sisencodigital.TaskSphere.entities.ReportStatus;
import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.entities.WeeklyReport;
import com.sisencodigital.TaskSphere.exceptions.BadRequestException;
import com.sisencodigital.TaskSphere.exceptions.ResourceNotFoundException;
import com.sisencodigital.TaskSphere.repositories.ProjectRepository;
import com.sisencodigital.TaskSphere.repositories.WeekProjRepository;
import com.sisencodigital.TaskSphere.securities.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final WeekProjRepository weekProjRepository;
    private final ProjectRepository projectRepository;
    private final CurrentUserProvider currentUserProvider;

    private WeeklyResponseDTO mapToDTO(WeeklyReport report){
        return WeeklyResponseDTO.builder()
                .id(report.getId())
                .userId(report.getUser().getId())
                .userFullName(report.getUser().getFullName())
                .projectId(report.getProject()!=null ? report.getProject().getId() : null)
                .projectName(report.getProject()!=null ? report.getProject().getName() : null)
                .weekStartDate(report.getWeekStartDate())
                .weekEndingDate(report.getWeekEndingDate())
                .taskPlanned(report.getTaskPlanned())
                .taskCompleted(report.getTaskCompleted())
                .hoursWorked(report.getHoursWorked())
                .bugs(report.getBugs())
                .notes(report.getNotes())
                .reportStatus(report.getReportStatus())
                .submittedAt(report.getSubmittedAt())
                .createdAt(report.getCreatedAt())
                .updatedAt(report.getUpdatedAt())
                .build();
    }

    public WeeklyResponseDTO createReport(WeeklyReportRequestDTO requestDTO) {
        User currentUser = currentUserProvider.getCurrentUser();
        weekProjRepository
                .findByUserIdAndWeekStartDate(currentUser.getId(),requestDTO.getWeekStartDate())
                .ifPresent(report -> {throw new BadRequestException("Report already exists for this week");
                });
        if(requestDTO.getWeekEndingDate().isBefore(requestDTO.getWeekStartDate())) {
            throw new BadRequestException("Week ending date cannot be before start date"
            );
        }
        Project project = null;
        if(requestDTO.getProjectId() != null){
            project = projectRepository
                    .findById(requestDTO.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        }
        WeeklyReport report = WeeklyReport.builder()
                .user(currentUser)
                .project(project)
                .weekStartDate(requestDTO.getWeekStartDate())
                .weekEndingDate(requestDTO.getWeekEndingDate())
                .taskPlanned(requestDTO.getTaskPlanned())
                .taskCompleted(requestDTO.getTaskCompleted())
                .hoursWorked(requestDTO.getHoursWorked())
                .bugs(requestDTO.getBugs())
                .notes(requestDTO.getNotes())
                .reportStatus(requestDTO.getReportStatus() != null ? requestDTO.getReportStatus() : ReportStatus.DRAFT)
                .build();

        if(report.getReportStatus() == ReportStatus.SUBMITTED){
            report.setSubmittedAt(LocalDateTime.now());}
        return mapToDTO(weekProjRepository.save(report));
    }



    public WeeklyResponseDTO updateReport(Long id, WeeklyReportRequestDTO requestDTO){
        WeeklyReport report=findById(id);
        Project project=null;
        if(requestDTO.getProjectId()!=null){
            project=projectRepository.findById(requestDTO.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        }
        report.setProject(project);
        report.setWeekStartDate(requestDTO.getWeekStartDate());
        report.setWeekEndingDate(requestDTO.getWeekEndingDate());
        report.setTaskPlanned(requestDTO.getTaskPlanned());
        report.setTaskCompleted(requestDTO.getTaskCompleted());
        report.setHoursWorked(requestDTO.getHoursWorked());
        report.setBugs(requestDTO.getBugs());
        report.setNotes(requestDTO.getNotes());

        if(requestDTO.getReportStatus()!=null){if(requestDTO.getReportStatus() == ReportStatus.SUBMITTED){
            report.setSubmittedAt(LocalDateTime.now());
            }
            report.setReportStatus(
                    requestDTO.getReportStatus()
            );
        }
        return mapToDTO(weekProjRepository.save(report));
    }


    public WeeklyResponseDTO submitReport(Long id){WeeklyReport report=findById(id);
        report.setReportStatus(ReportStatus.SUBMITTED
        );
        report.setSubmittedAt(LocalDateTime.now());
        return mapToDTO(weekProjRepository.save(report));
    }

    public WeeklyResponseDTO getReportById(Long id){
        return mapToDTO(findById(id));
    }


    public List<WeeklyResponseDTO> getMyReports(){
        Long userId = currentUserProvider.getCurrentUserId();
        return weekProjRepository
                .findByUser_IdOrderByWeekStartDateDesc(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    public void deleteReport(Long id){
        WeeklyReport report=findById(id);
        weekProjRepository.delete(report);
    }

    private WeeklyReport findById(Long id){
        return weekProjRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weekly report not found : "+id));
    }

}