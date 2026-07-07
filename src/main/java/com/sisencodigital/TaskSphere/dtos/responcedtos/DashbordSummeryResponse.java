package com.sisencodigital.TaskSphere.dtos.responcedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DashbordSummeryResponse {
    private long totalReportsSubmittedThisWeek;
    private long totalTeamMembers;
    private long pendingCount;
    private double submitionCompletedRate;
    private long openBugsCount;
}
