package com.ran.storage.platform.core.service.node.impl;

import com.ran.storage.platform.common.bean.entity.node.Node;
import com.ran.storage.platform.common.bean.po.node.NodePO;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.node.NodeService;
import com.ran.storage.platform.persist.mysql.node.NodeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseNodeService
 *
 * @author rwei
 * @since 2023/9/4 22:03
 */
public abstract class BaseNodeService implements NodeService {
    private static final Logger logger = LoggerFactory.getLogger(BaseNodeService.class);

    private final NodeDAO nodeDAO;

    public BaseNodeService(NodeDAO nodeDAO) {
        this.nodeDAO = nodeDAO;
    }

    @Override
    public Long addNodeToCluster(Node node, Long clusterId) {
        NodePO nodePO = ConvertUtils.obj2Obj(node, NodePO.class);
        nodeDAO.addAndSetId(nodePO);

        return nodePO.getId();
    }

    @Override
    public void removeNodeFromClusterById(Long nodeId) {
        nodeDAO.deleteById(nodeId);
    }

    @Override
    public abstract Boolean getNodeRunState(Long nodeId);
}
