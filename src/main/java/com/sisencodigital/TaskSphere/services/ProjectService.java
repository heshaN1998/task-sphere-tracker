package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.requestdtos.ProjectRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ProjectResponseDTO;
import com.sisencodigital.TaskSphere.entities.Project;
import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.exceptions.BadRequestException;
import com.sisencodigital.TaskSphere.exceptions.ResourceNotFoundException;
import com.sisencodigital.TaskSphere.repositories.ProjectRepository;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private ProjectResponseDTO toResponse(Project project) {
        return ProjectResponseDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .build();
    }
    private Project findByIdOrThrow(Long id){
        return projectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id + "project not found"));
    }



    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(this::toResponse).toList();

    }

    public ProjectResponseDTO getProjectById(Long id) {
        return toResponse(findByIdOrThrow(id));
    }

    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        if (projectRepository.existsByName(requestDTO.getName())) {
            throw new BadRequestException("A project with same name exist");
        }
        Project project = Project.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .build();
        return toResponse(projectRepository.save(project));
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
        Project project = findByIdOrThrow(id);
        project.setName(requestDTO.getName());
        project.setDescription(requestDTO.getDescription());
        return toResponse(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        Project project = findByIdOrThrow(id);
        projectRepository.delete(project);
    }

    @Transactional
    public ProjectResponseDTO assignMember(Long projectId,Long userId){
        Project project=findByIdOrThrow(projectId);
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(userId + "User not found"));
        project.getMembers().add(user);
        user.getProjects().add(project);
        userRepository.save(user);
        return toResponse(project);
    }

    @Transactional
    public ProjectResponseDTO removeMember(Long projectId,Long userId){
        Project project=findByIdOrThrow(projectId);
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(userId + "User not found"));
        project.getMembers().remove(user);
        user.getProjects().remove(project);
        userRepository.save(user);
        return toResponse(project);
    }
}
