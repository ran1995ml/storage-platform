package com.ran.storage.platform.common.bean.entity.result;

import com.ran.storage.platform.common.enums.ResultStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Result
 *
 * @author rwei
 * @since 2023/11/23 17:58
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -6771016784021901099L;

    private Integer code;

    private String message;

    private T data;

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
