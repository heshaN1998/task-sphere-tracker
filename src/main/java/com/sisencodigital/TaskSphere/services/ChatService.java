package com.sisencodigital.TaskSphere.services;

import com.sisencodigital.TaskSphere.ai.ReportAiTools;
import com.sisencodigital.TaskSphere.dtos.requestdtos.ChatRequestDTO;
import com.sisencodigital.TaskSphere.dtos.responcedtos.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private final ReportAiTools reportAiTools;
    private final ChatMemory chatMemory;

    public ChatService(ChatClient.Builder builder,
                       ReportAiTools reportAiTools,
                       ChatMemory chatMemory) {

        this.chatClient = builder.build();
        this.reportAiTools = reportAiTools;
        this.chatMemory = chatMemory;
    }


    public ChatResponseDto ask(ChatRequestDTO requestDTO){
        String responseDTO=chatClient
                .prompt()
                .user(requestDTO.getMessage())
                .tools(reportAiTools)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
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
