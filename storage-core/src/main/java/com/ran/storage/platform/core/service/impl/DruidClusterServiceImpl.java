package com.ran.storage.platform.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ran.storage.platform.common.bean.entity.Cluster;
import com.ran.storage.platform.common.bean.entity.Node;
import com.ran.storage.platform.common.bean.po.ClusterPO;
import com.ran.storage.platform.common.bean.po.NodePO;
import com.ran.storage.platform.common.component.KubernetesTool;
import com.ran.storage.platform.common.constant.DruidConstant;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.ClusterService;
import com.ran.storage.platform.core.service.ConfigService;
import com.ran.storage.platform.core.service.NodeService;
import com.ran.storage.platform.persist.mysql.ClusterDAO;
import com.ran.storage.platform.persist.mysql.NodeDAO;
import io.kubernetes.client.openapi.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * DruidClusterServiceImpl
 *
 * @author rwei
 * @since 2024/2/5 13:56
 */
@Service
public class DruidClusterServiceImpl implements ClusterService {
    private static final Logger logger = LoggerFactory.getLogger(DruidClusterServiceImpl.class);

    @Autowired
    private ClusterDAO clusterDAO;

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private ConfigService configService;

    @Autowired
    private NodeService nodeService;

    @Override
    public Long addCluster(ClusterPO clusterPO) throws IOException, ApiException {
        String config = configService.getLatestConfigFromCluster(clusterPO.getName());
        clusterPO.setConfig(config);
        return clusterDAO.addAndSetId(clusterPO);
    }

    @Override
    public List<Cluster> listAllClusters() {
        List<ClusterPO> clusterPOList = clusterDAO.selectList(null);
        return ConvertUtils.list2List(clusterPOList, Cluster.class);
    }

    @Override
    public Cluster getClusterByName(String clusterName) {
        QueryWrapper<ClusterPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DruidConstant.DRUID_CLUSTER_NAME_COLUMN, clusterName);
        List<ClusterPO> clusterPOS = clusterDAO.selectList(queryWrapper);
        if (clusterPOS.isEmpty()) {
            logger.warn("Non-existent cluster: {}", clusterName);
            return new Cluster();
        }
        return ConvertUtils.obj2Obj(clusterPOS.get(0), Cluster.class);
    }

    @Override
    public void deleteClusterByName(String clusterName) {

    }
}
