package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.model.Response;
import com.dutmdcjf.backendserver.model.Users.ChangePassword;
import com.dutmdcjf.backendserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @NonAuth
    @GetMapping("/detail")
    public Object getUserDetail(@RequestAttribute(name = "accountIdx") Long accountIdx) {
        return userService.getUserDetail(accountIdx);
    }

    @PutMapping("/changepassword")
    public Object changePassword(@RequestAttribute(name = "accountIdx") Long accountIdx, @RequestBody ChangePassword changePassword) {
        if (StringUtils.hasText(changePassword.getNewpassword()) || StringUtils.hasText(changePassword.getOldpassword())) {
            return new Response(400, "bad request");
        }

        return userService.changePassword(accountIdx, changePassword);
    }

}
