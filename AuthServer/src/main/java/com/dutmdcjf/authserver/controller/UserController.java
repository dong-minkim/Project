package com.dutmdcjf.authserver.controller;

import com.dutmdcjf.authserver.dto.User;
import com.dutmdcjf.authserver.exception.UserLoginException;
import com.dutmdcjf.authserver.exception.util.ErrorCode;
import com.dutmdcjf.authserver.jwt.AuthToken;
import com.dutmdcjf.authserver.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"AuthServer API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인, 토큰 발급", response = AuthToken.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "로그인 입력 정보가 잘못되었다"),
            @ApiResponse(code = 404, message = "사용자가 존재하지 않는다"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthToken login(@RequestBody User user) throws Exception {
        if(user == null || !StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword())) {
            throw new UserLoginException(ErrorCode.LOGIN_BAD_REQUEST);
        }
        return userService.userLogin(user.getUsername(), user.getPassword());
    }

    @ApiOperation(value = "토큰 재발급 요청", response = AuthToken.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "토큰 입력 정보가 잘못되었다"),
            @ApiResponse(code = 403, message = "Redis의 토큰과 입력된 토큰이 일치하지 않습니다"),
            @ApiResponse(code = 405, message = "Refresh 토큰이 만료되었습니다"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthToken refresh(@RequestBody AuthToken authToken) throws Exception {
        if (authToken == null) {
            throw new UserLoginException(ErrorCode.LOGIN_BAD_REQUEST);
        }
        return userService.refresh(authToken);
    }

    @ApiOperation(value = "로그아웃, 토큰 삭제")
    @ApiImplicitParam(name = "jwt", value = "refresh 토큰", required = true, dataType = "string", paramType = "header")
    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {
        userService.logout(request);
    }
}
