package com.ran.storage.platform.common.enums;

import lombok.Getter;

/**
 * ResultStatusEnum
 *
 * @author rwei
 * @since 2023/11/24 10:00
 */
@Getter
public enum ResultStatusEnum {
    SUCCESS(0, "success"),
    FAILED(1, "failed"),
    ;

    private final int code;

    private final String message;

    ResultStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
