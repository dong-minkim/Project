package com.dutmdcjf.backendserver.model.Users;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangePassword {
    private String oldpassword;
    private String newpassword;
}
