package com.playwright.demo.command;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.ai.tool.ToolCallbackProvider;

@ShellComponent
public class ChatCommand {

    private final ChatClient chatClient;
    private final ChatModel chatModel;

    public ChatCommand(ChatClient.Builder builder, ToolCallbackProvider tools, ChatModel chatModel) {
        this.chatModel = chatModel;
        this.chatClient = builder
                .defaultTools(tools)
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }


    @ShellMethod(key = "mcp", value="I will help you in automation")
    public String interactiveChat(String question) {
        return this.chatClient.prompt(question).call().content();
    }

}
