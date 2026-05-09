package com.nuliyang.agent.tools;

import com.nuliyang.agent.dto.WeatherRequest;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.resolution.StaticToolCallbackResolver;

import java.util.List;

public class CustomToolCallBackResolver extends StaticToolCallbackResolver {

    public CustomToolCallBackResolver(List<ToolCallback> toolCallbacks) {
        super(toolCallbacks);
    }

}
