package com.ran.storage.platform.core.service.node.impl;

import com.ran.storage.platform.common.bean.entity.node.Node;
import com.ran.storage.platform.common.bean.po.node.NodePO;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.node.NodeService;
import com.ran.storage.platform.persist.mysql.node.NodeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * NodeServiceImpl
 *
 * @author rwei
 * @since 2023/8/27 18:07
 */
@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private RestTool restTool;

    @Override
    public Long addNodeToCluster(Node node, Long clusterId) {
        NodePO nodePO = ConvertUtils.obj2Obj(node, NodePO.class);
        nodeDAO.addAndSetId(nodePO);

        return nodePO.getId();
    }

    @Override
    public void removeNodeFromCluster(Node node, Long nodeId) {
        nodeDAO.deleteById(nodeId);
    }

    @Override
    public Boolean getNodeRunState(Node node) {
        String host = node.getHost();
        Integer port = node.getPort();
        String path = String.format("%s:%s/status/health", host, port);
        return restTool.getForObject(path, new HashMap<>(), Boolean.class);
    }
}
