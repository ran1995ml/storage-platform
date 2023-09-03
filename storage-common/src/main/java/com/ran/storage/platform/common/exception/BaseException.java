package com.ran.storage.platform.common.exception;

import com.ran.storage.platform.common.enums.result.ResultStatus;

/**
 * BaseException
 *
 * @author rwei
 * @since 2023/8/25 11:21
 */
public class BaseException extends Exception {
    private final ResultStatus resultStatus;

    public BaseException(String message, Throwable cause, ResultStatus resultStatus) {
        super(message, cause);
        this.resultStatus = resultStatus;
    }

    public BaseException(String message, ResultStatus resultStatus) {
        super(message);
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return this.resultStatus;
    }
}
