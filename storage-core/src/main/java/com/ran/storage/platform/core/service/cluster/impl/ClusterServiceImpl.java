package com.ran.storage.platform.core.service.cluster.impl;

import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.entity.node.Node;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import com.ran.storage.platform.core.service.node.NodeService;
import com.ran.storage.platform.persist.mysql.cluster.ClusterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClusterServiceImpl
 *
 * @author rwei
 * @since 2023/8/25 10:45
 */
@Service
public class ClusterServiceImpl implements ClusterService {
    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    @Autowired
    private ClusterDAO clusterDAO;

    @Autowired
    private NodeService nodeService;

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
    public Long addCluster(Cluster cluster) {
        ClusterPO clusterPO = ConvertUtils.obj2Obj(cluster, ClusterPO.class);
        try {
            List<Node> nodes = cluster.getNodes();
            clusterDAO.addAndSetId(clusterPO);
            Long clusterId = clusterPO.getId();
            logger.info("Add cluster {} successfully", clusterId);
            addNodeToCluster(nodes, clusterId);
            return clusterId;
        } catch (DuplicateKeyException e) {
            logger.error("Error creating cluster {} duplicate key violation.", clusterPO.getId(), e);
            throw new DuplicateKeyException(String.format("Error cluster name %s duplicated", clusterPO.getName()));
        }
    }

    @Override
    public void removeClusterById(Long clusterId) {
        clusterDAO.deleteById(clusterId);
    }

    @Override
    public void updateClusterById(ClusterPO clusterPO) {
        try {
            int affectRow = clusterDAO.updateById(clusterPO);
            if (affectRow <= 0) {
                throw new NotExistException("cluster not exists");
            }
        } catch (NotExistException e) {

        }
    }

    private void addNodeToCluster(List<Node> nodes, Long clusterId) {
        for (Node node: nodes) {
            node.setClusterId(clusterId);
            nodeService.addNodeToCluster(node, clusterId);
        }
    }
}
