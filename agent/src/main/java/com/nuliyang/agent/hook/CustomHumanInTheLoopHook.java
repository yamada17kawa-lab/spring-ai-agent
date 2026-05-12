package com.nuliyang.agent.hook;


import com.alibaba.cloud.ai.graph.agent.hook.hip.HumanInTheLoopHook;
import com.alibaba.cloud.ai.graph.agent.hook.hip.ToolConfig;

public class CustomHumanInTheLoopHook {


    public static HumanInTheLoopHook getHumanInTheLoopHook() {

        return new HumanInTheLoopHook.Builder()
                .approvalOn("getWeatherTool",
                        ToolConfig.builder().description("Please confirm using the WeatherTool.").build())
                .build();

    }
}
