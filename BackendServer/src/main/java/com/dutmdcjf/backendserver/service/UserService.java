package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.dao.mapper.UserMapper;
import com.dutmdcjf.backendserver.model.Response;
import com.dutmdcjf.backendserver.model.Users.ChangePassword;
import com.dutmdcjf.backendserver.model.Users.UserInfoDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public UserInfoDetail getUserDetail(Long accountIdx) {
        UserInfoDetail userDetail = userMapper.getUserDetail(accountIdx);
        userDetail.setPassword(null);

        return userDetail;
    }

    public Response changePassword(Long accountIdx, ChangePassword changePassword) {
        UserInfoDetail userDetail = userMapper.getUserDetail(accountIdx);
        if (userDetail == null) {
            new Response (404, "Not found user");
        }

        if (userDetail.getPassword().equals(DigestUtils.md5DigestAsHex(changePassword.getOldpassword().getBytes(StandardCharsets.UTF_8)))) {
            new Response (405, "fail");
        }

        int ret = userMapper.setUserPassword(accountIdx, changePassword.getNewpassword());
        if (ret <= 0) {
            return new Response(405, "fail");
        }

        return new Response(200, "success");
    }
}
