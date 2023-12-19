package com.ran.storage.platform.core.service.cluster;

import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;

import java.util.List;

/**
 * ClusterService
 *
 * @author rwei
 * @since 2023/11/23 17:30
 */
public interface ClusterService {
    Long addCluster(ClusterPO clusterPO);

    List<Cluster> listAllClusters();

    Cluster getClusterById(Long clusterId);

    Result<Void> removeClusterById(Long clusterId, String operator);

    void updateClusterById(Long clusterId);
}
