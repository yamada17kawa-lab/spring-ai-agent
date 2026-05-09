package com.nuliyang.agent.tools;

import com.nuliyang.agent.dto.WeatherRequest;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsCallBackConfig {

    @Autowired
    private WeatherTool weatherTool;

    @Bean("toolCallback")
    public ToolCallback getToolCallback() {
        return FunctionToolCallback.builder("getWeatherTool",
                weatherTool)
                .inputType(WeatherRequest.class)
                .description("get weather info")
                .build();
    }
}
