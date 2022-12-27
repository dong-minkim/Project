package com.dutmdcjf.backendserver.interceptor;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.exception.AuthInterceptorException;
import com.dutmdcjf.backendserver.exception.util.ErrorCode;
import com.dutmdcjf.backendserver.jwt.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private static final int SECOND = 1000;
    private static final int ONE_MINUTE = 60;

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NonAuth nonAuth = handlerMethod.getMethodAnnotation(NonAuth.class);

        if (nonAuth != null) {
            // TEST
            request.setAttribute("accountIdx", 1);
            return true;
        }
        try {
            String accessToken = jwtProvider.getHeaderToken(request);
            if (accessToken == null) {
                throw new AuthInterceptorException(ErrorCode.JWT_NOT_EXIST);
            }

            log.info(accessToken);

            if (jwtProvider.isValidToken(accessToken)) {
                Long now = new Date(System.currentTimeMillis()).getTime();
                Long accessTokenExpiration = jwtProvider.getExpiration(accessToken).getTime();
                if ((accessTokenExpiration - now) / SECOND <= ONE_MINUTE) {
                    throw new AuthInterceptorException(ErrorCode.EXPIRE_ONE_MINUTE_LEFT);
                }

                String accountId = jwtProvider.getAccountId(accessToken); //accountId;
                //String accountEmail = jwtProvider.getEmail(accessToken);
                request.setAttribute("accountIdx", accountId);

                return true;
            }
        } catch (JwtException e) {
            throw new AuthInterceptorException(ErrorCode.JWT_EXPIRED);
        } catch (Exception e) {
            throw new AuthInterceptorException(ErrorCode.BAD_REQUEST);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
