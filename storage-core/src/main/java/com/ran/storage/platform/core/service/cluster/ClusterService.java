package com.ran.storage.platform.core.service.cluster;

import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;
import com.ran.storage.platform.common.bean.po.cluster.DruidClusterPO;

import java.util.List;

/**
 * DruidClusterService
 *
 * @author rwei
 * @since 2023/8/14 16:18
 */
public interface ClusterService<T> {
    List<T> listAllClusters();

    Long addCluster(T cluster);

    T getClusterById(Long clusterId);

    void updateCluster(T cluster);

    void deleteCluster(Long clusterId);
}
