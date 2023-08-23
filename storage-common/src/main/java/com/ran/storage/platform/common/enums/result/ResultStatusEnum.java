package com.ran.storage.platform.common.enums.result;

import com.ran.storage.platform.common.constant.Constant;
import lombok.Getter;

/**
 * ResultStatus
 *
 * @author rwei
 * @since 2023/8/8 16:31
 */
@Getter
public enum ResultStatusEnum {
    SUCCESS(Constant.SUCCESS, "success");

    private final int code;

    private final String message;

    ResultStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
