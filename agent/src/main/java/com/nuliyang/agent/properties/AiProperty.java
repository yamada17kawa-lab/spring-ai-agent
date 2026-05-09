package com.nuliyang.agent.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.ai.dashscope")
public class AiProperty {

    private String apiKey;
}
