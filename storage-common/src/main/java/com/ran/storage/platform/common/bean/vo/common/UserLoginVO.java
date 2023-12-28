package com.ran.storage.platform.common.bean.vo.common;

import com.ran.storage.platform.common.bean.vo.BaseVO;
import lombok.Data;

/**
 * UserLoginVO
 *
 * @author rwei
 * @since 2023/12/27 17:56
 */
@Data
public class UserLoginVO extends BaseVO {
    private String username;

    private String token;
}
