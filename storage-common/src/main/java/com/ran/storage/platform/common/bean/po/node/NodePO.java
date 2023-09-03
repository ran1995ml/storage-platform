package com.ran.storage.platform.common.bean.po.node;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * NodePO
 *
 * @author rwei
 * @since 2023/8/27 16:55
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "physical_node")
public class NodePO extends BasePO {
    protected Long clusterId;

    protected String host;

    protected Integer port;

    protected String role;
}
