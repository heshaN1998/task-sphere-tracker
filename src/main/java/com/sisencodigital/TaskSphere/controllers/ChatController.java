package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.requestdtos.ChatRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ChatResponseDto;
import com.sisencodigital.TaskSphere.services.ChatService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/aichat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    public ResponseEntity<ChatResponseDto> chat(@Valid @RequestBody ChatRequestDTO requestDTO) {
        return ResponseEntity.ok(chatService.ask(requestDTO));
    }
}
