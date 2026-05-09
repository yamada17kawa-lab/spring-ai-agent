package com.nuliyang.agent.tools;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Supplier;

@Configuration(proxyBeanMethods = false)
public class CupTool {

    @Bean("getCupColor")
    @Description("get the cup's color")
    public Supplier<String> getCupColor(){
        return new Supplier<String>() {
            @Override
            public String get() {
                return "cup color is red";
            }
        };
    }

    @Bean("getCupSize")
    @Description("get the cup's size")
    public Supplier<String> getCupSize(){
        return new Supplier<String>() {
            @Override
            public String get() {
                return "cup size is 1L";
            }
        };
    }


}
