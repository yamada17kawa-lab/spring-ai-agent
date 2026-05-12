package com.nuliyang.agent.skills;

import com.alibaba.cloud.ai.graph.agent.hook.skills.SkillsAgentHook;
import com.alibaba.cloud.ai.graph.skills.registry.classpath.ClasspathSkillRegistry;

public class CustomSkillsAgentHook  {


    public static SkillsAgentHook getSkillsAgentHook() {
        // 添加自定义技能
        ClasspathSkillRegistry registry = ClasspathSkillRegistry.builder()
                .basePath("skills")
                .build();

        return SkillsAgentHook.builder()
                .skillRegistry(registry)
                .build();
    }
}
