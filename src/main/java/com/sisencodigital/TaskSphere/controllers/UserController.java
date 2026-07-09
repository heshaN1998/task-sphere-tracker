package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.responcedtos.UserResponseDTO;
import com.sisencodigital.TaskSphere.entities.Role;
import com.sisencodigital.TaskSphere.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/team-members")
    public ResponseEntity<List<UserResponseDTO>> getTeamMembers(){
        return ResponseEntity.ok(userService.getTeamMembers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/{id}/role")
    public  ResponseEntity<UserResponseDTO> updateRole(@PathVariable Long id, @RequestParam Role role){
        return ResponseEntity.ok(userService.updateRole(id,role));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<UserResponseDTO> setActive(@PathVariable Long id,@RequestParam boolean active){
        return ResponseEntity.ok(userService.setActive(id,active));
    }




}
