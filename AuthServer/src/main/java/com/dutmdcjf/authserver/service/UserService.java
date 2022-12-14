package com.dutmdcjf.authserver.service;

import com.dutmdcjf.authserver.common.Encrypt;
import com.dutmdcjf.authserver.common.StringDefiner;
import com.dutmdcjf.authserver.dto.mapper.UserMapper;
import com.dutmdcjf.authserver.exception.UserLoginException;
import com.dutmdcjf.authserver.exception.util.ErrorCode;
import com.dutmdcjf.authserver.jwt.AuthToken;
import com.dutmdcjf.authserver.jwt.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Value("${jwt.accessToken.exp}")
    private String accessTokenExp;
    @Value("${jwt.refreshToken.exp}")
    private String refreshTokenExp;
    @Value("${spring.redis.exp}")
    private Long redisExp;

    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    public AuthToken userLogin(String username, String password) throws Exception {
        Map<String, Object> userLoginData;
        userLoginData = userMapper.getUserByLoginData(username, new Encrypt().encryptBySha256(password));
        if (userLoginData == null) {
            throw new UserLoginException(ErrorCode.NOT_FOUND_USER);
        }

        String userId = String.valueOf(userLoginData.get("idx"));
        String accessToken = jwtProvider.createToken(userId, accessTokenExp);
        String refreshToken = jwtProvider.createToken(userId, refreshTokenExp);

        String redisKey = StringDefiner.REDIS_ID_PREFIX + userId;
        String redisAuthToken = objectMapper.writeValueAsString(new AuthToken(accessToken, refreshToken));
        redisService.setValues(redisKey, redisAuthToken, redisExp);

        return new AuthToken(accessToken, refreshToken);
    }

    public AuthToken refresh(AuthToken authToken) throws Exception {
        String newAccessToken = null;

        try {
            if (!jwtProvider.isValidToken(authToken.getRefreshToken())) {
                log.info("refreshToken??? ??????????????? ?????????");
            }

            String userId = jwtProvider.getUserId(authToken.getRefreshToken());
            String redisKey = StringDefiner.REDIS_ID_PREFIX + userId;
            String redisAuthToken = String.valueOf(redisService.getValues(redisKey));
            System.out.println(redisAuthToken);

            if (redisAuthToken == null) {
                throw new UserLoginException(ErrorCode.JWT_EXPIRED);
            }

            String redisRefreshToken = objectMapper.readValue(redisAuthToken, AuthToken.class).getRefreshToken();

            if (!redisRefreshToken.equals(authToken.getRefreshToken())) {
                log.info("refreshToken??? ????????????, ??????????????? ?????? ?????? ??????");
                throw new UserLoginException(ErrorCode.DO_NOT_MATCHING_TOKEN);
            }

            newAccessToken = jwtProvider.createToken(userId, accessTokenExp);
            String newRedisAuthToken = objectMapper.writeValueAsString(new AuthToken(newAccessToken, authToken.getRefreshToken()));
            redisService.setValues(redisKey, newRedisAuthToken, redisExp);

        } catch (ExpiredJwtException | NullPointerException e) {
            log.warn(e.getMessage());
            throw new UserLoginException(ErrorCode.JWT_EXPIRED);
        } catch (UserLoginException e) {
            System.out.println("????????? 4");
            throw e;
        } catch (Exception e) {
            System.out.println("????????? 5");
            throw e;
        }

        return new AuthToken(newAccessToken, null);
    }

    /*
     * HTTP Header??? refreshToken??? ?????????.
     * */
    public void logout(HttpServletRequest request) {
        String refreshToken = jwtProvider.getHeaderToken(request);
        try {
            if (refreshToken != null) {
                String userId = jwtProvider.getUserId(refreshToken);
                String redisKey = StringDefiner.REDIS_ID_PREFIX + userId;

                String redisAuthToken = String.valueOf(redisService.getValues(redisKey));
                AuthToken authTokenInRedis = objectMapper.readValue(redisAuthToken, AuthToken.class);
                if (authTokenInRedis.getRefreshToken().equals(refreshToken)) {
                    redisService.delValues(redisKey);
                }
            }
        } catch (Exception e) {
            log.warn("", e);
        }
    }
}
