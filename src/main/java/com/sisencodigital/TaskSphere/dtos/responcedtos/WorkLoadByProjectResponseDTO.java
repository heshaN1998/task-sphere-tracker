package com.sisencodigital.TaskSphere.dtos.responcedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkLoadByProjectResponseDTO {
    private String projectName;
    private  long reportCount;
    //long returned type because jpql query
}
