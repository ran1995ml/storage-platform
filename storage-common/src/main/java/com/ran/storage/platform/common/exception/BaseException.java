package com.ran.storage.platform.common.exception;

import com.ran.storage.platform.common.enums.ResultStatusEnum;

/**
 * BaseException
 *
 * @author rwei
 * @since 2023/12/26 14:19
 */
public class BaseException extends Exception {
    private final ResultStatusEnum resultStatusEnum;

    public BaseException(String message, Throwable cause, ResultStatusEnum resultStatusEnum) {
        super(message, cause);
        this.resultStatusEnum = resultStatusEnum;
    }


    public BaseException(String message, ResultStatusEnum resultStatusEnum) {
        super(message);
        this.resultStatusEnum = resultStatusEnum;
    }

    public ResultStatusEnum getResultStatusEnum() {
        return resultStatusEnum;
    }
}
