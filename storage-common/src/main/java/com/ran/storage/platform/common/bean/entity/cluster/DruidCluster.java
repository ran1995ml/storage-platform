package com.ran.storage.platform.common.bean.entity.cluster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DruidCluster
 *
 * @author rwei
 * @since 2023/8/7 22:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DruidCluster extends Cluster {
    private String coordinator;

    private String overlord;

    private String router;

    private String broker;

    private String historical;

    private String middleManager;
}
