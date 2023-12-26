package com.ran.storage.platform.common.exception;

import com.ran.storage.platform.common.enums.ResultStatusEnum;

/**
 * NotExistException
 *
 * @author rwei
 * @since 2023/12/26 14:22
 */
public class NotExistException extends BaseException {
    public NotExistException(String message) {
        super(message, ResultStatusEnum.NOT_EXIST);
    }
}
