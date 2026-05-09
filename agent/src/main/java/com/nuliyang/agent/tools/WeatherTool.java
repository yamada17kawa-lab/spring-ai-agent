package com.nuliyang.agent.tools;


import com.nuliyang.agent.dto.WeatherRequest;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;


@Component
public class WeatherTool implements BiFunction<WeatherRequest, ToolContext, String> {

    @Tool(description = "get weather by city name")
    @Override
    public String apply(@ToolParam(description = "cityName") WeatherRequest weatherRequest, ToolContext toolContext) {
        return "it's always sunny in " + weatherRequest.getCityName();
    }
}
