package com.nuliyang.agent.tools;

import org.jetbrains.annotations.NotNull;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;

import java.util.List;

public class CustomToolCallBackProvider implements ToolCallbackProvider {

    private final List<ToolCallback> toolCallbacks;

    public CustomToolCallBackProvider(List<ToolCallback> toolCallbacks) {
        this.toolCallbacks = toolCallbacks;
    }

    @NotNull
    @Override
    public ToolCallback[] getToolCallbacks() {
        return this.toolCallbacks.toArray(new ToolCallback[0]);
    }
}
