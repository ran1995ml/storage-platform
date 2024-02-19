package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

import java.util.Date;

/**
 * NodePO
 *
 * @author rwei
 * @since 2024/2/15 18:35
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "node_basic")
public class NodePO extends BasePO {
    protected String nodeName;

    protected String clusterName;

    protected String ip;

    protected String hostname;

    protected String status;

    protected String nodeType;

    protected Date startTime;

    protected int failureCount;

    protected int consecutiveFailureCount = 0;

    protected int consecutiveSuccessCount = 0;

    protected boolean isReady;

    protected boolean isHealthy;
}
