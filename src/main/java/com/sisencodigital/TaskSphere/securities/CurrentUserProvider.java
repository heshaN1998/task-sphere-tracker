package com.sisencodigital.TaskSphere.securities;

import com.sisencodigital.TaskSphere.entities.User;
import com.sisencodigital.TaskSphere.exceptions.ResourceNotFoundException;
import com.sisencodigital.TaskSphere.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider {
    private final UserRepository userRepository;

    public User getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
        return userRepository.findById(userPrincipal.getId()).orElseThrow(()->new ResourceNotFoundException("Authenticated user no longer exists"));

    }
    public Long getCurrentUserId(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal=(UserPrincipal) authentication.getPrincipal();
        return principal.getId();
    }
    public boolean isManager(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
        return "MANAGER".equals(userPrincipal.getRole());
    }


}
