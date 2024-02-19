package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;
import lombok.ToString;

/**
 * UserPO
 *
 * @author rwei
 * @since 2023/12/26 13:25
 */

@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "user")
public class UserPO extends BasePO {
    private String username;

    private String password;

    private String role;
}
