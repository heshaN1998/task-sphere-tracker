package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.requestdtos.LoginRequestDTO;
import com.sisencodigital.TaskSphere.dtos.requestdtos.RegisterRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.AuthResponseDTO;
import com.sisencodigital.TaskSphere.entities.Role;
import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.exceptions.BadRequestException;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import com.sisencodigital.TaskSphere.securities.JwtUtil;
import com.sisencodigital.TaskSphere.securities.UserPrincipal;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new BadRequestException("An account wth this email exists");
        }
        Role role = requestDTO.getRole() != null ? requestDTO.getRole() : Role.TEAM_MEMBER;

        User user = User.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .role(role)
                .active(true)
                .build();
        user = userRepository.save(user);

        user = userRepository.save(user);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        String token = jwtUtil.generateToken(userPrincipal, user.getId(), user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .tokenType("Bearer ")
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword())
        );
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(
                () -> new BadRequestException("Invalid email or password")
        );
        UserPrincipal userPrincipal = new UserPrincipal(user);
        String token = jwtUtil.generateToken(userPrincipal, user.getId(), user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .tokenType("Bearer ")
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}


