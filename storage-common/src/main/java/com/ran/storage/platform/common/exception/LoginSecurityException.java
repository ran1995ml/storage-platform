package com.ran.storage.platform.common.exception;

import com.ran.storage.platform.common.enums.ResultStatusEnum;

/**
 * LoginSecurityException
 *
 * @author rwei
 * @since 2023/12/26 16:48
 */
public class LoginSecurityException extends BaseException {
    public LoginSecurityException(String message) {
        super(message, ResultStatusEnum.USER_CREDENTIALS_ERROR);
    }
}
