package com.ran.storage.platform.common.bean.dto.common;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import lombok.Data;

/**
 * UserRegisterDTO
 *
 * @author rwei
 * @since 2023/12/26 15:19
 */
@Data
public class UserRegisterDTO extends BaseDTO {
    private String username;

    private String password;
}
