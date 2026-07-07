package com.sisencodigital.TaskSphere.repositories;

import com.sisencodigital.TaskSphere.entities.WeeklyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeekProjRepository extends JpaRepository<WeeklyReport,Long>, JpaSpecificationExecutor<WeeklyReport> {
}
