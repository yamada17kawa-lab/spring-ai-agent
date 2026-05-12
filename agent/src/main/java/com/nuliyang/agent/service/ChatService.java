package com.nuliyang.agent.service;


import com.alibaba.cloud.ai.graph.NodeOutput;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.alibaba.cloud.ai.graph.streaming.OutputType;
import com.alibaba.cloud.ai.graph.streaming.StreamingOutput;
import com.nuliyang.agent.AgentApplication;
import com.nuliyang.agent.model.CustomChatModel;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ChatService {


    @Autowired
    private CustomChatModel customChatModel;

    public Flux<String> chat(String userQuery) throws GraphRunnerException {

        System.out.println("开始调用dashscope api");


        ReactAgent agent = customChatModel.getReactAgent();


        Flux<NodeOutput> outputFlux = agent.stream(new UserMessage(
                userQuery
        ));


        return outputFlux.flatMap(output -> {
            if (output instanceof StreamingOutput<?> streamingOutput){
                OutputType outputType = streamingOutput.getOutputType();
                Message message = streamingOutput.message();

                if (outputType == OutputType.AGENT_MODEL_STREAMING){
                    if (message instanceof AssistantMessage assistantMessage){
                        Object reasoningContext = assistantMessage.getMetadata().get("reasoningContext");
                        if (reasoningContext != null && !reasoningContext.toString().isEmpty()){
                            if (message.getText() != null && !message.getText().isEmpty()){
                                System.out.print("[Thinking] " + message.getText());
                                return Flux.just("[Thinking] " + message.getText());
                            }
                        }else {
                            if (message.getText() != null && !message.getText().isEmpty()){
                                System.out.print(assistantMessage.getText());
                                return Flux.just(assistantMessage.getText());
                            }
                        }
                    }
                }else if (outputType == OutputType.AGENT_HOOK_FINISHED) {
                    if (message instanceof ToolResponseMessage toolResponseMessage){
                        toolResponseMessage.getResponses().forEach(toolResponse -> {
                            System.out.println("工具调用结果" + toolResponse.name() + "==>" +toolResponse.responseData());
                        });
                    }
                }else if (outputType == OutputType.AGENT_MODEL_FINISHED){
                    if (message instanceof AssistantMessage assistantMessage){
                        if (assistantMessage.hasToolCalls()){
                            assistantMessage.getToolCalls().forEach(toolCall -> {
                                System.out.println("调用" + toolCall.name() + "工具" + "参数：" + toolCall.arguments());
                            });
                        }else {
                            System.out.println("\n模型输出完成");
                        }
                    }

                } else if (outputType == OutputType.AGENT_TOOL_FINISHED) {
                    if (message instanceof ToolResponseMessage toolResponseMessage){
                        toolResponseMessage.getResponses().forEach(toolResponse -> {
                            System.out.println("工具调用结果" + toolResponse.name() + "==>" +toolResponse.responseData());
                        });
                    }
                }
            }
            return Flux.empty();
        });
    }
}
