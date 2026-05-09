package com.nuliyang.agent;

import com.alibaba.cloud.ai.graph.NodeOutput;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.alibaba.cloud.ai.graph.streaming.OutputType;
import com.alibaba.cloud.ai.graph.streaming.StreamingOutput;
import com.nuliyang.agent.model.CustomChatModel;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class AgentApplication {


    public static void main(String[] args) throws GraphRunnerException {
        ConfigurableApplicationContext context = SpringApplication.run(AgentApplication.class, args);

        System.out.println("开始调用dashscope api");

        CustomChatModel customChatModel = context.getBean(CustomChatModel.class);

        ReactAgent agent = customChatModel.getReactAgent();


        Flux<NodeOutput> outputFlux = agent.stream(new UserMessage("今天，广州天河区天气怎么样"));

        AtomicInteger flag = new AtomicInteger(6);

        outputFlux.subscribe(
                output -> {
                    if (output instanceof StreamingOutput<?> streamingOutput){
                        OutputType outputType = streamingOutput.getOutputType();
                        Message message = streamingOutput.message();
//                        if (flag.get() > 0){
//                            flag.decrementAndGet();
//                            System.out.println("输出类型outputType: " + outputType);
//                        }



                        if (outputType == OutputType.AGENT_MODEL_STREAMING){
                            if (message instanceof AssistantMessage assistantMessage){
                                Object reasoningContext = assistantMessage.getMetadata().get("reasoningContext");
                                if (reasoningContext != null && !reasoningContext.toString().isEmpty()){
                                    if (message.getText() != null){
                                        System.out.println("[thinking] " + message.getText());
                                    }
                                }else {
                                    if (message.getText() != null && !message.getText().isEmpty()){
                                        System.out.println(assistantMessage.getText());
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

                        }
                    }else {
                        System.out.println("outType 不是 StreamingOutput 类型: " + output.getClass().getName());
                    }

                },
                error -> {
                    System.out.println("error:" + error.getMessage());
                },
                () -> {
                    System.out.println("Agent调用完成");
                }
        );

        outputFlux.blockLast();


    }


}
