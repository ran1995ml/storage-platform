package com.ran.storage.platform.core.service.cluster;

import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;

import java.util.List;

/**
 * ClusterService
 *
 * @author rwei
 * @since 2023/8/24 17:59
 */
public interface ClusterService {
    List<Cluster> listAllClusters();

    Cluster getClusterById(Long clusterId);

    Long addCluster(Cluster cluster);

    void removeClusterById(Long clusterId);

    void updateClusterById(ClusterPO clusterPO);
}
