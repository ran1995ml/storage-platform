package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ran.storage.platform.common.bean.po.BasePO;
import com.ran.storage.platform.common.constant.Constant;
import lombok.Data;

/**
 * ClusterPO
 *
 * @author rwei
 * @since 2024/2/5 13:01
 */
@Data
@TableName(Constant.MYSQL_TABLE_NAME_PREFIX + "cluster_basic")
public class ClusterPO extends BasePO {
    protected String name;

    protected String description;

    protected String image;

    protected String config;

    protected String kubernetesCertificate;

    protected String kubernetesNamespace;
}
