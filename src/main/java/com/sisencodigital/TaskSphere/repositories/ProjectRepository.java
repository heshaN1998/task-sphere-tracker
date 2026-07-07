package com.sisencodigital.TaskSphere.repositories;

import com.sisencodigital.TaskSphere.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
