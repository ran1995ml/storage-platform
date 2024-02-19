package com.ran.storage.platform.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ran.storage.platform.common.bean.entity.Cluster;
import com.ran.storage.platform.common.bean.entity.Node;
import com.ran.storage.platform.common.bean.po.NodePO;
import com.ran.storage.platform.common.component.KubernetesTool;
import com.ran.storage.platform.common.constant.DruidConstant;
import com.ran.storage.platform.common.enums.DruidNodeTypeEnum;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.common.utils.DateUtils;
import com.ran.storage.platform.core.service.ClusterService;
import com.ran.storage.platform.core.service.NodeService;
import com.ran.storage.platform.persist.mysql.ClusterDAO;
import com.ran.storage.platform.persist.mysql.NodeDAO;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DruidNodeServiceImpl
 *
 * @author rwei
 * @since 2024/2/6 09:55
 */
@Service
public class DruidNodeServiceImpl implements NodeService {
    private static final Logger logger = LoggerFactory.getLogger(DruidNodeServiceImpl.class);

    @Autowired
    private KubernetesTool kubernetesTool;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private NodeDAO nodeDAO;

    @Override
    public String getLogFromNode() {

        return null;
    }

    @Override
    public Node getNodeInfo() {
        return null;
    }

    @Transactional
    @Override
    public void updateNodesToDB(String clusterName) {
        try {
            nodeDAO.deleteByCluster(clusterName);
            List<Node> nodes = listAllNodesFromCluster(clusterName);
            List<NodePO> nodePOS = ConvertUtils.list2List(nodes, NodePO.class);
            nodeDAO.batchInsert(nodePOS);
            logger.info("Update nodes info for cluster {} successfully", clusterName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Node> listAllNodesFromDB(String clusterName) {
        QueryWrapper<NodePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cluster_name", clusterName);
        List<NodePO> nodePOS = nodeDAO.selectList(queryWrapper);
        return ConvertUtils.list2List(nodePOS, Node.class);
    }

    @Override
    public List<Node> listAllNodesFromCluster(String clusterName) throws IOException, ApiException {
        Cluster cluster = clusterService.getClusterByName(clusterName);
        List<V1Pod> v1Pods = kubernetesTool.listNamespacedPod(cluster.getKubernetesCertificate(),
                cluster.getKubernetesNamespace());
        return v1Pods.stream().map(v1Pod -> {
            Node node = new Node();
            node.setClusterName(clusterName);
            node.setNodeName(v1Pod.getMetadata().getName());
            node.setIp(v1Pod.getStatus().getPodIP());
            node.setHostname(v1Pod.getSpec().getNodeName());
            node.setStatus(v1Pod.getStatus().getPhase());
            node.setStartTime(DateUtils.offsetDate2Date(v1Pod.getStatus().getStartTime()));
            node.setReady(kubernetesTool.isPodReady(v1Pod.getStatus().getContainerStatuses()));
            node.setNodeType(getNodeTypeFromName(v1Pod.getMetadata().getName()));
            node.setHealthy(isNodeHealthy(node.isReady(), node.getConsecutiveFailureCount()));
            return node;
        }).filter(node->Objects.nonNull(node.getNodeType())).collect(Collectors.toList());
    }

    private String getNodeTypeFromName(String nodeName) {
        return Arrays.stream(nodeName.split("-"))
                .filter(DruidNodeTypeEnum::contains)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private boolean isNodeHealthy(boolean isReady, int consecutiveFailureCount) {
        return isReady && consecutiveFailureCount < 3;
    }
}
