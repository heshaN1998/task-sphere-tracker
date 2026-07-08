package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.ai.ReportAiTools;
import com.sisencodigital.TaskSphere.dtos.requestdtos.ChatRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final ReportAiTools reportAiTools;

    public ChatResponseDto ask(ChatRequestDTO requestDTO){
        String responseDTO=chatClient
                .prompt()
                .user(requestDTO.getMessage())
                .tools(reportAiTools)
                .call()
                .content();
                //WRONG   .tools(reportAiTools);
                //we don't need to use tool if we coded ai congiguration

        return ChatResponseDto.builder()
                .answer(responseDTO)
                .timestamp(java.time.LocalDateTime.now().toString())
                .build();

    }
}
