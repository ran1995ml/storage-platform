package com.ran.storage.platform.common.bean.po.cluster;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * DruidClusterPO
 *
 * @author rwei
 * @since 2023/8/14 15:23
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "druid_physical_cluster")
public class DruidClusterPO extends BasePO {
    private String name;

    private String description;

    private String coordinator;

    private String overlord;

    private String router;

    private String broker;

    private String historical;

    private String middleManager;

    private String zookeeper;
}
