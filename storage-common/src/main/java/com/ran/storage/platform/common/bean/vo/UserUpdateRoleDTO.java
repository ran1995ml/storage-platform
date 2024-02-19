package com.ran.storage.platform.common.bean.vo;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import lombok.Data;

/**
 * UserUpdateRoleDTO
 *
 * @author rwei
 * @since 2023/12/26 15:56
 */
@Data
public class UserUpdateRoleDTO extends BaseDTO {
    private Long id;

    private String username;

    private String role;
}
