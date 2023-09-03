package com.ran.storage.platform.common.enums.result;

import lombok.Getter;

/**
 * ResultStatus
 *
 * @author rwei
 * @since 2023/8/8 16:31
 */
@Getter
public enum ResultStatus {
    SUCCESS(1, "success"),

    FAILED(0, "failed"),

    NOT_EXIST(1001, "not exists");

    private final int code;

    private final String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
