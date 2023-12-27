package com.ran.storage.platform.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.ran.storage.platform.common.bean.entity.common.User;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.constant.UserConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * TokenUtils
 *
 * @author rwei
 * @since 2023/12/26 22:08
 */
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    private static final String JWT_TOKEN_SECRET = "sso-storage-platform";

    private static final String JWT_ISSUER = "auth0";

    private static final String JWT_USERNAME_CLAIM = "Username";

    private static final String JWT_PASSWORD_CLAIM = "Password";

    public static String sign(User user) {
        Date expiredDate = new Date(System.currentTimeMillis() + UserConstant.USER_TOKEN_EXPIRED_TIME);
        return JWT.create().withIssuer(JWT_ISSUER)
                .withClaim(JWT_USERNAME_CLAIM, user.getUsername())
                .withClaim(JWT_PASSWORD_CLAIM, user.getPassword())
                .withExpiresAt(expiredDate)
                .sign(Algorithm.HMAC256(JWT_TOKEN_SECRET));
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_TOKEN_SECRET)).withIssuer(JWT_ISSUER).build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTokenExpired(String token) {
        Date expiredDate = JWT.decode(token).getExpiresAt();
        return !Objects.isNull(expiredDate) && expiredDate.before(Date.from(Instant.now()));
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        String token = sign(user);
        System.out.println(verify(token));
        System.out.println(isTokenExpired(token));
        String format = String.format("%s/**", ApiPrefix.API_V1_PREFIX);
        System.out.println(format);
    }
}
