package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.responcedtos.DashbordSummeryResponse;
import com.sisencodigital.TaskSphere.entities.ReportStatus;
import com.sisencodigital.TaskSphere.entities.Role;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import com.sisencodigital.TaskSphere.repositories.WeekProjRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashbordService {
    private final WeekProjRepository weekProjRepository;
    private final UserRepository userRepository;

    public DashbordSummeryResponse getSummary(LocalDate weekStartDate) {
        long totalTeamMembers = userRepository.findByRole(Role.TEAM_MEMBER).size();

        long submitted = weekProjRepository.countByWeekStartDateAndReportStatus(weekStartDate, ReportStatus.SUBMITTED);
        long pending = totalTeamMembers - submitted;
        double submitionCompletedRate = 0;

        if (totalTeamMembers > 0) {
            submitionCompletedRate = (submitted * 100.0) / totalTeamMembers;
        }

        long openBugs = weekProjRepository.findOpenBugsForWeek(weekStartDate).size();

            return DashbordSummeryResponse.builder()
                .totalReportsSubmittedThisWeek(submitted)
                .totalTeamMembers(totalTeamMembers)
                .pendingCount(Math.max(pending, 0))
                .submitionCompletedRate(
                        Math.round(submitionCompletedRate * 100.0) / 100.0)
                .openBugsCount(openBugs)
                .build();
    }
}
