package com.sisencodigital.TaskSphere.repositories;


import com.sisencodigital.TaskSphere.entities.ReportStatus;
import com.sisencodigital.TaskSphere.entities.WeeklyReport;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
public class WeeklyReportSpecifications {
    private WeeklyReportSpecifications() {
    }
    public static Specification<WeeklyReport> withFilters(Long userId,
                                                          Long projectId,
                                                          LocalDate weekStartDate,
                                                          LocalDate fromDate,
                                                          LocalDate toDate,
                                                          ReportStatus reportStatus) {
                return (root, query, cb) -> {
                var predicates = cb.conjunction();

                if (userId != null) {
                predicates = cb.and(predicates, cb.equal(root.get("user").get("id"), userId));
                }
                if (projectId != null) {
                predicates = cb.and(predicates, cb.equal(root.get("project").get("id"), projectId));
                }
                if (weekStartDate != null) {
                predicates = cb.and(predicates, cb.equal(root.get("weekStartDate"), weekStartDate));
                }
                if (fromDate != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("weekStartDate"), fromDate));
                }
                if (toDate != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("weekEndingDate"), toDate));
                }
                if (reportStatus != null) {
                predicates = cb.and(predicates, cb.equal(root.get("status"), reportStatus));
                }
                return predicates;
        };
    }
}
