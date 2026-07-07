package com.sisencodigital.TaskSphere.dtos.responcedtos;

import com.sisencodigital.TaskSphere.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
private String token;
private String tokenType;
private Long userId;
private String fullName;
private String email;

private Role role;

}
