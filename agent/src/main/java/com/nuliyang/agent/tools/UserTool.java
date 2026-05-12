package com.nuliyang.agent.tools;


import com.nuliyang.agent.dto.UserToolRequest;
import com.nuliyang.agent.dto.UserToolResp;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UserTool implements BiFunction<UserToolRequest, ToolContext, UserToolResp> {


    @Tool(name = "getUserInfo",
    description = "get user's information by username.")
    @Override
    public UserToolResp apply(@ToolParam(description = "用户名") UserToolRequest userToolRequest, ToolContext toolContext) {
        return new UserToolResp().setUserName(userToolRequest.getUsername())
                .setEmail("34653478@qq.com")
                .setPhone("124352546");
    }
}
