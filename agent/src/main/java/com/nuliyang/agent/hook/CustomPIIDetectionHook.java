package com.nuliyang.agent.hook;

import com.alibaba.cloud.ai.graph.agent.hook.pii.PIIDetectionHook;
import com.alibaba.cloud.ai.graph.agent.hook.pii.PIIType;
import com.alibaba.cloud.ai.graph.agent.hook.pii.RedactionStrategy;

public class CustomPIIDetectionHook {

    public static PIIDetectionHook getPIIDetectionHook() {
        return new PIIDetectionHook.Builder()
                .piiType(PIIType.EMAIL)
                .strategy(RedactionStrategy.MASK)
                .applyToToolResults( true)
                .build();
    }
}
