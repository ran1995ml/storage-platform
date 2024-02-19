package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * OperateLogPO
 *
 * @author rwei
 * @since 2024/1/9 13:53
 */

@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "operate_log")
public class OperateLogPO extends BasePO {
    private String operateUsername;

    private String operateModule;

    private String operateType;

    private String detail;
}
