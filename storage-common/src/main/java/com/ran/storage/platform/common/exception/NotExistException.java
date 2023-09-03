package com.ran.storage.platform.common.exception;

import com.ran.storage.platform.common.enums.result.ResultStatus;

/**
 * NotExistException
 *
 * @author rwei
 * @since 2023/8/25 11:29
 */
public class NotExistException extends BaseException {
    public NotExistException(String message) {
        super(message, ResultStatus.NOT_EXIST);
    }
}
