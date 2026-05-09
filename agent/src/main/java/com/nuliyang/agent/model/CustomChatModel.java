package com.nuliyang.agent.model;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.agent.hook.modelcalllimit.ModelCallLimitHook;
import com.nuliyang.agent.properties.AiProperty;
import com.nuliyang.agent.tools.ToolsCallBackConfig;
import lombok.Data;
import lombok.Getter;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class CustomChatModel {


    private final ReactAgent reactAgent;


    public CustomChatModel(AiProperty aiProperty,
                           @Qualifier("toolCallback")ToolCallback tools) {
        // 创建 DashScope API 实例
        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(aiProperty.getApiKey())
                .build();

        // 创建 ChatModel

        DashScopeChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(DashScopeChatOptions.builder()
                        .model("qwen-plus")
                        .maxToken(2048)
                        .enableThinking(true)
//                        .enableSearch(true)
                        .topK(2)
                        .temperature(0.7)
                        .build())
                .build();

        //创建Agent
        this.reactAgent = ReactAgent.builder()
                .name("my_agent")
                .model(chatModel)
                .hooks(ModelCallLimitHook.builder().runLimit(3).build())
                //使用agent就把tool绑在agent里面
                .tools(List.of(tools))
                .build();


    }


}
