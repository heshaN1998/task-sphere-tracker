package com.sisencodigital.TaskSphere.controllers;

import com.sisencodigital.TaskSphere.dtos.requestdtos.ChatRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ChatResponseDto;
import com.sisencodigital.TaskSphere.services.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/aichat")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDto> chat(@Valid @RequestBody ChatRequestDTO requestDTO) {
        return ResponseEntity.ok(chatService.ask(requestDTO));
    }
}
