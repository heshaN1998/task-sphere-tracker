package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.responcedtos.UserResponseDTO;
import com.sisencodigital.TaskSphere.securities.CurrentUserProvider;
import com.sisencodigital.TaskSphere.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(){
        Long userId=currentUserProvider.getCurrentUserId();
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
