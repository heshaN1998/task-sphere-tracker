package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.dtos.requestdtos.ChatRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    public ChatClient chatClient;

    public ChatResponseDto ask(ChatRequestDTO requestDTO){
        String responseDTO=chatClient
                .prompt()
                .user(requestDTO.getMessage())
                .call()
                .content();

        return ChatResponseDto.builder()
                .answer(responseDTO)
                .timestamp(java.time.LocalDateTime.now().toString())
                .build();

    }
}
