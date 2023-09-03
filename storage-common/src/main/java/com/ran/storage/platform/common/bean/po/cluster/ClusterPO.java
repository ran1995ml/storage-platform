package com.ran.storage.platform.common.bean.po.cluster;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * ClusterPO
 *
 * @author rwei
 * @since 2023/8/20 20:14
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "physical_cluster")
public class ClusterPO extends BasePO {
    protected String name;

    protected String type;

    protected String description;
}
