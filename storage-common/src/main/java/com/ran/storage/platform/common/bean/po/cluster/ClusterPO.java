package com.ran.storage.platform.common.bean.po.cluster;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * ClusterPO
 *
 * @author rwei
 * @since 2023/11/24 11:10
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "physical_cluster")
public class ClusterPO extends BasePO {
    private String type;

    private String name;

    private String description;
}
