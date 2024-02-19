package com.ran.storage.platform.core.service;

import com.ran.storage.platform.common.bean.entity.Cluster;
import com.ran.storage.platform.common.bean.po.ClusterPO;
import com.ran.storage.platform.common.exception.NotExistException;
import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;
import java.util.List;

/**
 * DruidClusterService
 *
 * @author rwei
 * @since 2024/2/5 13:48
 */
public interface ClusterService {
    Long addCluster(ClusterPO clusterPO) throws IOException, ApiException;

    List<Cluster> listAllClusters();

    Cluster getClusterByName(String clusterName);

    void deleteClusterByName(String clusterName);
}
