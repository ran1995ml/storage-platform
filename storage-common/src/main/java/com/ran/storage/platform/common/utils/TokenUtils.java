package com.ran.storage.platform.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ran.storage.platform.common.bean.entity.common.User;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.constant.UserConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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

    public static Optional<String> decode(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JWT_TOKEN_SECRET)).withIssuer(JWT_ISSUER).build().verify(token);
            return Optional.of(decodedJWT.getClaim(JWT_USERNAME_CLAIM).asString());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isTokenExpired(String token) {
        try {
            Date expiredDate = JWT.decode(token).getExpiresAt();
            return !Objects.isNull(expiredDate) && expiredDate.before(Date.from(Instant.now()));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        String token = sign(user);
        System.out.println(decode(token));
    }
}
