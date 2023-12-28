package com.ran.storage.platform.common.bean.dto.common;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import lombok.Data;

/**
 * UserLoginDTO
 *
 * @author rwei
 * @since 2023/12/26 13:39
 */
@Data
public class UserLoginDTO extends BaseDTO {
    private String username;

    private String password;
}
