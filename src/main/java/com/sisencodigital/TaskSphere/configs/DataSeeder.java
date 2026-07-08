package com.sisencodigital.TaskSphere.configs;


import com.sisencodigital.TaskSphere.entities.Project;
import com.sisencodigital.TaskSphere.entities.Role;
import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.repositories.ProjectRepository;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    public class DataSeeder implements CommandLineRunner {
        private final UserRepository userRepository;
        private final ProjectRepository projectRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) {
            if (userRepository.count() == 0) {
                User manager = User.builder()
                        .fullName("Default Manager")
                        .email("manager@company.com")
                        .password(passwordEncoder.encode("Manager@123"))
                        .role(Role.MANAGER)
                        .active(true)
                        .build();
                userRepository.save(manager);
            }

            if (projectRepository.count() == 0) {
                projectRepository.save(Project.builder().name("Internal Tooling").description("Internal tools and platform work").build());
                projectRepository.save(Project.builder().name("Client A").description("Client A engagement").build());
                projectRepository.save(Project.builder().name("R&D").description("Research and prototyping").build());
            }
        }
    }
