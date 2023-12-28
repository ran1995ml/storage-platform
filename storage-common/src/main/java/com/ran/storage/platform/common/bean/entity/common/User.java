package com.ran.storage.platform.common.bean.entity.common;

import lombok.Data;

/**
 * User
 *
 * @author rwei
 * @since 2023/12/26 13:35
 */
@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String role;
}
