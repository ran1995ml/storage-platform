package com.ran.storage.platform.common.constant;

/**
 * UserConstant
 *
 * @author rwei
 * @since 2023/12/26 15:30
 */
public class UserConstant {
    private UserConstant() {}

    public static final String DEFAULT_USER_ROLE_NAME = "member";

    public static final String USERNAME_COLUMN = "username";

    public static final String USERID_COLUMN = "id";

    public static final Integer USER_SESSION_MAX_INACTIVE_INTERVAL = 60 * 60;

    public static final String USER_LOGIN_SESSION_KEY = "X-SSO-STORAGE-PLATFORM-USER";
}
