package com.sisencodigital.TaskSphere.repositories;

import com.sisencodigital.TaskSphere.entities.ReportStatus;
import com.sisencodigital.TaskSphere.entities.WeeklyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeekProjRepository extends JpaRepository<WeeklyReport,Long>, JpaSpecificationExecutor<WeeklyReport> {
    List<WeeklyReport> findByUser_IdOrderByWeekStartDateDesc(Long userId);
    Optional<WeeklyReport> findByUserIdAndWeekStartDate(Long userId,LocalDate weekStartDate);
    List<WeeklyReport> findByWeekStartDateAndReportStatus(LocalDate weekStartDate, ReportStatus status);
    long countByWeekStartDateAndReportStatus(LocalDate weekStartDate, ReportStatus reportStatus);

    @Query("SELECT COUNT(DISTINCT r.user.id)" +
            "FROM WeeklyReport r " +
            "WHERE r.weekStartDate=:weekStart " +
            "AND r.reportStatus=:status")
    long countUsersByWeekStartDateAndReportStatus(@Param("weekStart") LocalDate weekStart, @Param("status") ReportStatus Status);

    @Query("SELECT r FROM WeeklyReport r " +
            "WHERE r.weekStartDate=:weekStart")
    List <WeeklyReport> findAllForWeek(@Param("weekStart") LocalDate weekStart);

    @Query("SELECT r.project.name,COUNT(r) " +
            "FROM WeeklyReport r"+ " WHERE r.weekStartDate " +
            "BETWEEN :start and :end AND r.project is not null " +
            " GROUP BY r.project.name")
    List<Object[]> countReportsGroupedByProject(@Param("start") LocalDate Start,@Param("end") LocalDate end);

    @Query("""
             SELECT r.weekStartDate,COUNT(r)
             FROM WeeklyReport r
             WHERE r.reportStatus=:status
             AND (:userId IS NULL OR r.user.id=:userId)
             AND r.weekStartDate BETWEEN :start AND :end
             GROUP BY r.weekStartDate
             ORDER BY r.weekStartDate ASC
             """)
    List<Object[]> tasksCompletedTrend(@Param("userId") Long userId,
                                       @Param("status") ReportStatus reportStatus,
                                       @Param("start") LocalDate start,
                                       @Param("end") LocalDate end);


    @Query("SELECT r FROM WeeklyReport r " +
            "WHERE r.bugs is not null and r.bugs <>'' " +
            "AND r.weekStartDate=:weekStart")
    List <WeeklyReport> findOpenBugsForWeek(@Param("weekStart") LocalDate weekStart);
}

