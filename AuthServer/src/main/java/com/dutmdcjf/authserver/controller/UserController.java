package com.dutmdcjf.authserver.controller;

import com.dutmdcjf.authserver.dto.User;
import com.dutmdcjf.authserver.exception.UserLoginException;
import com.dutmdcjf.authserver.exception.util.ErrorCode;
import com.dutmdcjf.authserver.jwt.AuthToken;
import com.dutmdcjf.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthToken login(@RequestBody User user) throws Exception {
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            throw new UserLoginException(ErrorCode.LOGIN_BAD_REQUEST);
        }
        return userService.userLogin(user.getEmail(), user.getPassword());
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthToken refresh(@RequestBody AuthToken authToken) throws Exception {
        if (authToken == null) {
            throw new UserLoginException(ErrorCode.LOGIN_BAD_REQUEST);
        }
        return userService.refresh(authToken);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {
        userService.logout(request);
    }
}
