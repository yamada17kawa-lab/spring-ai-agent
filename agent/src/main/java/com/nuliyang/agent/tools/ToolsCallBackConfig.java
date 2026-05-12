package com.nuliyang.agent.tools;

import com.nuliyang.agent.dto.UserToolRequest;
import com.nuliyang.agent.dto.WeatherRequest;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ToolsCallBackConfig {


    @Bean("toolCallback")
    public List<ToolCallback> getToolCallback(WeatherTool weatherTool,
                                              UserTool userTool) {
        ArrayList<ToolCallback> toolCallbackArrayList = new ArrayList<>();

        toolCallbackArrayList.add(FunctionToolCallback.builder("getWeatherTool",
                        weatherTool)
                .inputType(WeatherRequest.class)
                .description("get weather info")
                .build());

        toolCallbackArrayList.add(FunctionToolCallback.builder("getUserInfo",
                        userTool)
                .inputType(UserToolRequest.class)
                .description("get user info")
                .build());

        Method getCurrentTime = ReflectionUtils.findMethod(DataTimeTool.class, "getCurrentTime", String.class);
        toolCallbackArrayList.add(MethodToolCallback.builder()
                .toolDefinition(ToolDefinitions.builder(getCurrentTime)
                        .build())
                .toolMethod(getCurrentTime)
                .toolObject(new DataTimeTool())
                .build());


        return toolCallbackArrayList;
    }
}
