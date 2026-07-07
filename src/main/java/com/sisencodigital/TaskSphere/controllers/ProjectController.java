package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.requestdtos.ProjectRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ProjectResponseDTO;
import com.sisencodigital.TaskSphere.entities.Project;
import com.sisencodigital.TaskSphere.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO requestDTO) {
    return new ResponseEntity<>(projectService.createProject(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,@Valid @RequestBody ProjectRequestDTO requestDTO){
        return ResponseEntity.ok(projectService.updateProject(id,requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/Members/{userId}")
    public ResponseEntity <ProjectResponseDTO> assignMember(@PathVariable Long id,@PathVariable Long userId){
        return ResponseEntity.ok(projectService.assignMember(id,userId));
    }

    @DeleteMapping("/{id}/Members/{userId}")
    public ResponseEntity<ProjectResponseDTO> removeMember(@PathVariable Long id,@PathVariable Long userId){
        return ResponseEntity.ok(projectService.removeMember(id,userId));
    }

}
