package com.nuliyang.agent.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataTimeTool {

    @Tool(description = "get somewhere's current time")
    public String getCurrentTime(@ToolParam(description = "cityName") String cityName) {
        return cityName + "的当前时间是：" +LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
