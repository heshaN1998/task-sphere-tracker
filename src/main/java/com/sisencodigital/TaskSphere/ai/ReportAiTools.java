package com.sisencodigital.TaskSphere.ai;

import com.sisencodigital.TaskSphere.entities.ReportStatus;
import com.sisencodigital.TaskSphere.repositories.WeekProjRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportAiTools {
    private final WeekProjRepository weekProjRepository;

    @Tool(description = "Get submitted reports in this week")
    public long getSubmittedReports() {
        LocalDate weekStart = LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() - 1);
        return weekProjRepository.countByWeekStartDateAndReportStatus(weekStart, ReportStatus.SUBMITTED);
    }

    @Tool(description = "Get weekly report that have open bugs")
    public List<String> getOpenBugs(){
        LocalDate today=LocalDate.now();
        LocalDate weekStart=today.minusDays(today.getDayOfWeek().getValue()-1);
        return weekProjRepository
                .findOpenBugsForWeek(weekStart)
                .stream()
                .map(weeklyReport -> weeklyReport.getUser().getFullName() + " : " + weeklyReport.getBugs())
                .toList();
    }
}
