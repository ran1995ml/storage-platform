package com.ran.storage.platform.common.bean.vo.common;

import com.ran.storage.platform.common.bean.vo.BaseVO;
import lombok.Data;

/**
 * UserDisplayVO
 *
 * @author rwei
 * @since 2023/12/26 14:34
 */
@Data
public class UserDisplayVO extends BaseVO {
    private String username;

    private String role;
}
