package com.ran.storage.platform.common.bean.dto.cluster;

import com.ran.storage.platform.common.bean.po.BasePO;
import lombok.Data;

/**
 * DruidClusterDTO
 *
 * @author rwei
 * @since 2023/8/15 16:21
 */
@Data
public class DruidClusterDTO extends ClusterDTO {
    protected String coordinator;

    protected String overlord;

    protected String router;

    protected String broker;

    protected String historical;

    protected String middleManager;
}
