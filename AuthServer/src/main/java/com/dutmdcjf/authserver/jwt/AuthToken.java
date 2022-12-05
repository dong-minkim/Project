package com.dutmdcjf.authserver.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String accessToken;

    @ApiModelProperty(value = "refresh 토큰", required = true)
    private String refreshToken;
}
