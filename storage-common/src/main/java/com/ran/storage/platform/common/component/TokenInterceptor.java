package com.ran.storage.platform.common.component;

import com.ran.storage.platform.common.constant.Constant;
import com.ran.storage.platform.common.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * TokenInterceptor
 *
 * @author rwei
 * @since 2023/12/27 15:00
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    private static final String TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        String token = request.getHeader(TOKEN_KEY);
        if (Objects.isNull(token)) {
            logger.info("None-token request, skip request");
            response.setStatus(Constant.HTTP_UNAUTHORIZED_STATUS_CODE);
            try {
                response.getWriter().write("Unauthorized: Missing Token");
                response.getWriter().flush();
            } catch (Exception e) {
                logger.error("Error setting response content");
            }
            return false;
        }

        if (TokenUtils.isTokenExpired(token)) {
            logger.info("Token {} is expired, skip request", token);
            response.setStatus(Constant.HTTP_UNAUTHORIZED_STATUS_CODE);
            return false;
        }

        if (!TokenUtils.verify(token)) {
            logger.info("Token {} is invalid", token);
            response.setStatus(Constant.HTTP_UNAUTHORIZED_STATUS_CODE);
            return false;
        }

        response.setStatus(Constant.HTTP_OK_STATUS_CODE);
        return true;
    }
}
