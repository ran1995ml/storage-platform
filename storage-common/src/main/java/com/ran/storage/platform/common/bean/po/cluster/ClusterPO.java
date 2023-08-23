package com.ran.storage.platform.common.bean.po.cluster;

import com.ran.storage.platform.common.bean.po.BasePO;
import lombok.Data;

/**
 * ClusterPO
 *
 * @author rwei
 * @since 2023/8/20 20:14
 */
@Data
public class ClusterPO extends BasePO {
    protected String name;

    protected String description;
}
