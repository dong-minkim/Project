package com.dutmdcjf.authserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class User {

    @ApiModelProperty(value = "유저 번호")
    private Long idx;

    @ApiModelProperty(value = "아이디(이메일)", example = "test@gmail.com",required = true)
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "01230123", required = true)
    private String password;

    private String email;
}
