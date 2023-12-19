package com.ran.storage.platform.core.service.cluster.impl;

import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import com.ran.storage.platform.persist.mysql.cluster.ClusterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClusterServiceImpl
 *
 * @author rwei
 * @since 2023/11/24 14:23
 */
@Service
public class DruidClusterService implements ClusterService {
    private static final Logger logger = LoggerFactory.getLogger(DruidClusterService.class);

    @Autowired
    private ClusterDAO clusterDAO;

    @Override
    public Long addCluster(ClusterPO clusterPO) {
        clusterDAO.addAndSetId(clusterPO);
        return clusterPO.getId();
    }

    @Override
    public List<Cluster> listAllClusters() {
        List<ClusterPO> clusterPOS = clusterDAO.selectList(null);
        return ConvertUtils.list2List(clusterPOS, Cluster.class);
    }

    @Override
    public Cluster getClusterById(Long clusterId) {
        return ConvertUtils.obj2Obj(clusterDAO.selectById(clusterId), Cluster.class);
    }

    @Override
    public Result<Void> removeClusterById(Long clusterId, String operator) {
        return null;
    }

    @Override
    public void updateClusterById(Long clusterId) {

    }
}
