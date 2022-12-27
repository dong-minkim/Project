package com.dutmdcjf.backendserver.model.Users;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDetail {
    private String company;
    private String lastname;
    private String firstname;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private BigDecimal discount;
    private Long idx;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
