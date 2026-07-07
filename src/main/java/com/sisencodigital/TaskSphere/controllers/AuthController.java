package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.requestdtos.LoginRequestDTO;
import com.sisencodigital.TaskSphere.dtos.requestdtos.RegisterRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.AuthResponseDTO;
import com.sisencodigital.TaskSphere.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register (@Valid @RequestBody RegisterRequestDTO requestDTO){
        return new ResponseEntity<>(authService.register(requestDTO), HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@Valid @RequestBody LoginRequestDTO requestDTO){
        return ResponseEntity.ok(authService.login(requestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity <Void> logout(){
        return ResponseEntity.noContent().build();
    }
}
