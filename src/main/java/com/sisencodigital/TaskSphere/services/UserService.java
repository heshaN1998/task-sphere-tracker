package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.responcedtos.UserResponseDTO;
import com.sisencodigital.TaskSphere.entities.Role;
import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.exceptions.ResourceNotFoundException;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private UserResponseDTO toResponse(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }


    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<UserResponseDTO> getTeamMembers(){
        return userRepository.findByRole(Role.TEAM_MEMBER).stream().map(this::toResponse).toList();
    }

    public UserResponseDTO getUserById(Long id){
        User user =userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found" + id ));
        return toResponse(user);
    }

    public UserResponseDTO updateRole(Long id,Role role){
        User user=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found" + id));
        user.setRole(role);
        return toResponse(userRepository.save(user));
    }

    public UserResponseDTO setActive(Long id,boolean active){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found" + id ));
        user.setActive(active);
        return toResponse(userRepository.save(user));

    }


}
