package com.ran.storage.platform.common.bean.entity.result;

import com.ran.storage.platform.common.enums.result.ResultStatusEnum;
import lombok.Data;

/**
 * Result
 *
 * @author rwei
 * @since 2023/8/8 17:37
 */
@Data
public class Result<T> {
    private int code;

    private String message;

    private T data;

    private Result() {
        this.code = ResultStatusEnum.SUCCESS.getCode();
        this.message = ResultStatusEnum.SUCCESS.getMessage();
    }

    public static <T> Result<T> buildSuccess() {
        Result<T> result = new Result<>();
        result.setCode(ResultStatusEnum.SUCCESS.getCode());
        result.setMessage(ResultStatusEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> buildSuccess(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatusEnum.SUCCESS.getCode());
        result.setMessage(ResultStatusEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
}
