package com.ran.storage.platform.common.bean.entity.result;

import com.ran.storage.platform.common.enums.result.ResultStatus;
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
        this.code = ResultStatus.SUCCESS.getCode();
        this.message = ResultStatus.SUCCESS.getMessage();
    }

    public static <T> Result<T> buildSuccess() {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.SUCCESS.getCode());
        result.setMessage(ResultStatus.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> buildSuccess(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultStatus.SUCCESS.getCode());
        result.setMessage(ResultStatus.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
}
