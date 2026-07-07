package com.sisencodigital.TaskSphere.repositories;

import com.sisencodigital.TaskSphere.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
