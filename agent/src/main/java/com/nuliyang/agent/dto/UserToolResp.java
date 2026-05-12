package com.nuliyang.agent.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserToolResp {


    private String userName;

    private String email;

    private String phone;
}
